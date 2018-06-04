package shop4j.enums;

import java.util.stream.Stream;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 16:08
 * @Description:登陆状态枚举
 */
public enum LoginStatusEnum {
    Success(1,"登陆成功！"),

    Error(2,"用户名，密码不存在或错误！"),

    CountFreeze(3,"账户已冻结！请联系管理员或重置密码解冻"),

    CheckCodeError(4,"验证码输入错误！"),

    CheckParamError(5,"参数格式校验出错！"),

    FiveCountError(6,"当日连续输错5次，账号冻结！"),

    ThreeCountError(7,"连续输错3次！"),
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

    public static LoginStatusEnum findByStatus(int status){
        for (LoginStatusEnum statusEnum:values()) {
            if(statusEnum.getStatus()==status)
                return statusEnum;
        }
        return null;
    }
}
