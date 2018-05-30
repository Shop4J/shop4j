package shop4j.vo.login;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:23
 * @Description:
 */
@Data
public class LoginVO {
    @NotEmpty(message = "手机号不能为空！")
    @Size(min=11,max=11,message = "手机号为11位数的号码！")
    @Pattern(regexp="/^[1][3,4,5,7,8][0-9]{9}$/",message = "手机号非法！")
    private String phone;

    @NotEmpty(message = "密码不能为空！")
    @Size(min=6,message = "密码不能低于6位！")
    private String password;

    @NotEmpty(message = "验证码不能为空！")
    @Size(min=1,message = "验证码错误！")
    private String checkCode;
}
