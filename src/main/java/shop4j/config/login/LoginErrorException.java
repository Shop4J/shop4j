package shop4j.config.login;

import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author: weixuedong
 * @Date: 2018/6/1 10:32
 * @Description: 自定义登陆异常
 */
@Data
public class LoginErrorException extends AuthenticationException {
    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 辅助参数1，辅助参数由不同得状态码决定其含义
     */
    private int paramOne;

    public LoginErrorException(int code,String msg,int paramOne) {
        super(msg);
        this.msg=msg;
        this.code=code;
        this.paramOne=paramOne;
    }

    public LoginErrorException(int code,String msg) {
        super(msg);
        this.msg=msg;
        this.code=code;
    }
}
