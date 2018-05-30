package shop4j.services.login;

import shop4j.result.LoginResult;
import shop4j.vo.login.LoginVO;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:04
 * @Description:登陆业务
 */
public interface LoginService {
    LoginResult login(LoginVO loginVO);
}
