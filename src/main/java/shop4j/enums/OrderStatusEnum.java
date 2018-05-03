package shop4j.enums;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 18:22
 * @Description:订单状态
 */
public enum OrderStatusEnum {
    WAITPAY(1,"待付款"),

    WAITSEND(2,"待发货"),

    WAITRECIVER(3,"待收货"),

    FINISH(4,"完成"),

    CLOSE(5,"已关闭"),

    REFUND(6,"已退款"),
    ;
    private int status;

    private String name;

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

    OrderStatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
