package shop4j.enums;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 15:48
 * @Description:站点信息类型
 */
public enum  WebInfoTypeEnum {

    Shop(1,"电商平台"),

    Manage(2,"后台管理站")

    ;
    private int type;

    private String msg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    WebInfoTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
