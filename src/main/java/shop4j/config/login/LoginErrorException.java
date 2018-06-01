package shop4j.config.login;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: weixuedong
 * @Date: 2018/6/1 10:32
 * @Description:
 */
public class LoginErrorException extends AuthenticationException {
    public LoginErrorException(String msg) {
        super(msg);
    }
}
