package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 11:03
 * @Description:子品 sku
 */
@Table(name = "db_products.tbl_product_kid")
@Data
public class ProductKid extends BaseModel{

    /**
     * 价格 元
     */
    @Column
    private BigDecimal price;

    /**
     * 库存
     */
    @Column
    private int num;

    @Column
    private long spuId;

    /**
     * 是否为主品 1是 2否
     */
    @Column
    private int isMain;
}
