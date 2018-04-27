package shop4j.enums;

import java.io.Serializable;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:36
 * @Description:公共数据状态
 */
public enum CommonDataStatus implements Serializable{
    OK(1,"可用"),

    NO(2,"不可用"),

    DELETE(3,"删除")
    ;
    private int status;

    private String name;

    CommonDataStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
