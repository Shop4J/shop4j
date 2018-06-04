package shop4j.config.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import shop4j.enums.LoginStatusEnum;
import shop4j.services.login.LoginService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @Author: weixuedong
 * @Date: 2018/6/1 10:15
 * @Description:重写验证机制
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();  // 如上面的介绍，这里通过authentication.getDetails()获取详细信息
        String token = details.getToken();//验证码
        String password = (String) authentication.getCredentials();
        String username = (String) authentication.getPrincipal();
        UserDetails user = loginService.loadUserByUsername(username);
        if(Objects.isNull(user) || !user.getPassword().equals(password)){

            throw new LoginErrorException(LoginStatusEnum.Error.getStatus()+"");

        }else if(!user.isEnabled()){

            throw new LoginErrorException(LoginStatusEnum.CountFreeze.getStatus()+"");

        } else {
            return new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
