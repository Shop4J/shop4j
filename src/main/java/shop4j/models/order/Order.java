package shop4j.models.order;

import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 18:18
 * @Description:订单
 */
@Table(name = "db_order.tbl_order")
public class Order extends BaseModel{

    /**
     * 订单总价
     */
    @Column
    private BigDecimal priceTotal;

    /**
     * 重写父类状态
     * @see shop4j.enums.OrderStatusEnum
     */
    @Column
    private int status;

    /**
     * 买家编号
     */
    @Column
    private long buyerId;

    /**
     * 预留姓名
     */
    @Column
    private String name;

}
