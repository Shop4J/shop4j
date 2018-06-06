package shop4j.config.login;

import base.util.collections.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import shop4j.enums.CommonDataStatus;
import shop4j.enums.LoginStatusEnum;
import shop4j.models.user.LoginLog;
import shop4j.models.user.User;
import shop4j.services.login.LoginLogService;
import shop4j.services.login.LoginService;
import shop4j.services.user.UserService;
import shop4j.utils.IpUtil;
import shop4j.vo.login.UserDetailVO;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: weixuedong
 * @Date: 2018/6/1 10:15
 * @Description:重写验证机制
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private UserService userService;
    @Transactional(noRollbackFor=AuthenticationException.class)
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();  // 如上面的介绍，这里通过authentication.getDetails()获取详细信息
        String token = details.getToken();//验证码
        String password = (String) authentication.getCredentials();
        String username = (String) authentication.getPrincipal();
        UserDetailVO user =(UserDetailVO) loginService.loadUserByUsername(username);

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        if(Objects.isNull(user)){
            loginLogService.log(IpUtil.getIpAddr(request),username, CommonDataStatus.NO);
            throw new LoginErrorException(LoginStatusEnum.Error.getStatus(),LoginStatusEnum.Error.getMsg());

        }else if(!user.isEnabled()){
            loginLogService.log(IpUtil.getIpAddr(request),username, CommonDataStatus.NO);
            throw new LoginErrorException(LoginStatusEnum.CountFreeze.getStatus(),LoginStatusEnum.CountFreeze.getMsg());

        }

        if( !user.getPassword().equals(password)){
            List<LoginLog> logs = loginLogService.findTodayLogs(username);
            if(CollectionUtil.isNotEmpty(logs)){
                int errorTimes = 0;//错误次数
                boolean isContinuous=true;//是否连续
                for(int index=0;index<logs.size();index++){
                    if(isContinuous){
                        LoginLog log = logs.get(index);
                        if(log.getStatus()==CommonDataStatus.OK.getStatus()){
                            isContinuous=false;
                            break;
                        }
                        if(log.getStatus()==CommonDataStatus.NO.getStatus()){
                            errorTimes++;
                        }
                    }else {
                        break;
                    }
                }
                if(errorTimes>=4){
                    userService.freezeUser(user.getUserId());
                    loginLogService.log(IpUtil.getIpAddr(request),username, CommonDataStatus.NO);
                    throw new LoginErrorException(LoginStatusEnum.FiveCountError.getStatus(),LoginStatusEnum.FiveCountError.getMsg(),errorTimes);
                }
                if(errorTimes>=2){
                    loginLogService.log(IpUtil.getIpAddr(request),username, CommonDataStatus.NO);
                    throw new LoginErrorException(LoginStatusEnum.ThreeCountError.getStatus(),LoginStatusEnum.ThreeCountError.getMsg(),5-(errorTimes+1));
                }
            }
            loginLogService.log(IpUtil.getIpAddr(request),username, CommonDataStatus.NO);
            throw new LoginErrorException(LoginStatusEnum.Error.getStatus(),LoginStatusEnum.Error.getMsg());

        }else {
            loginLogService.log(IpUtil.getIpAddr(request),username, CommonDataStatus.OK);
            return new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
