package shop4j.enums;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:08
 * @Description:登陆状态枚举
 */
public enum LoginStatusEnum {
    Success(1,"登陆成功！"),

    Error(2,"用户名，密码不存在或错误！"),


    CountFreeze(3,"账户已冻结！请联系管理员"),

    CheckCodeError(4,"验证码输入错误！"),

    CheckParamError(5,"参数格式校验出错！"),

    ;
    private int status;

    private String msg;

    LoginStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
