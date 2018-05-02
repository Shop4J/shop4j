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
@Table(name = "db_products.tbl_kid_product")
@Data
public class KidProduct extends BaseModel{
    /**
     * 颜色
     */
    @Column
    private int color;

    /**
     * 价格 元
     */
    @Column
    private BigDecimal price;

    /**
     * 父类编号
     */
    @Column
    private long spu;

    /**
     * 库存
     */
    @Column
    private int num;
}
