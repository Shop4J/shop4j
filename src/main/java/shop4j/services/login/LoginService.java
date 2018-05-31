package shop4j.services.login;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import shop4j.result.LoginResult;
import shop4j.vo.login.LoginVO;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:04
 * @Description:登陆业务
 */
@Service
public interface LoginService extends UserDetailsService {
    LoginResult login(LoginVO loginVO);
}
