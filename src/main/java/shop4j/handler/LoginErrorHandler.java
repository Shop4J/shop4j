package shop4j.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import shop4j.enums.LoginStatusEnum;
import shop4j.result.LoginResult;

import javax.validation.ConstraintViolationException;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:56
 * @Description:登陆错误管理
 */
@ControllerAdvice
public class LoginErrorHandler {
    /**
     * 登陆参数格式问题
     * 返回json
     * @return json返回
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public LoginResult loginErrorHandler(ConstraintViolationException e){
        LoginResult loginResult = new LoginResult();
        loginResult.setMsg(e.getMessage());
        loginResult.setStatus(LoginStatusEnum.CheckParamError.getStatus());
        return loginResult;
    }
}
