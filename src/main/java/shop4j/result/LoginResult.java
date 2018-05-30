package shop4j.result;

import lombok.Data;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:06
 * @Description:登陆结果
 */
@Data
public class LoginResult {
    /**
     * 登陆状态码
     */
    private int status;

    /**
     * 信息
     */
    private String msg;

    /**
     * 验证码
     */
    private String checkCode;
}
