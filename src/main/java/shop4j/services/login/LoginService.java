package shop4j.services.login;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.stereotype.Service;
import shop4j.result.LoginResult;
import shop4j.vo.login.LoginVO;
import shop4j.vo.login.UserDetailVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:04
 * @Description:登陆业务
 */
@Service
public interface LoginService extends UserDetailsService {
    LoginResult login(LoginVO loginVO);

    /**
     * 获得当前登陆用户
     * @return
     */
    UserDetailVO getCurrentLogin();

}
