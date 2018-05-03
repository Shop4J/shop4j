package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 14:12
 * @Description:父类商品
 */
@Data
@Table(name = "db_products.tbl_products")
public class Product extends BaseModel{
    /**
     * 产品名称
     */
    @Column
    private String name;

    /**
     * 产品类型
     * @SEE ProductType
     */
    @Column
    private int type;

    /**
     * 商品描述
     */
    @Column
    private String detail;

    /**
     * 展示价
     */
    @Column
    private BigDecimal showPrice;

    /**
     * @see shop4j.enums.OrderStatusEnum
     */
    @Column
    private int status;

    /**
     * 买家编号
     */
    @Column
    private long userId;

    /**
     * 收货人手机号
     */
    @Column
    private String mobile;

    /**
     * 收货人地址
     */
    @Column
    private String addr;

    @Column
    private long spuId;
}
