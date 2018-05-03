package shop4j.models.order;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: weixuedong
 * @Date: 2018/5/3 10:24
 * @Description:订单详情
 */
@Table(name = "tbl_order_detail")
@Data
public class OrderDetail extends BaseModel {
    /**
     * SKU
     */
    @Column
    private long skuId;

    /**
     * 总价
     */
    @Column
    private BigDecimal priceTotal;

    /**
     * 购买数量
     */
    @Column
    private int num;

    /**
     * SPU
     */
    @Column
    private long spuId;

    /**
     * 订单编号
     */
    @Column
    private long orderId;

    /**
     * 商品单价
     */
    @Column
    private BigDecimal unitPrice;
}
