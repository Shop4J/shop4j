package shop4j.models.products;

import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/17 18:39
 * @Description:商品参数值
 */
@Table(name = "db_products.tbl_product_param_value")
public class ProductParamValue extends BaseModel{
    /**
     * 参数编号
     */
    @Column
    private long paramId;

    /**
     * 值
     */
    @Column
    private String value;

    @Column
    private long productId;

    /**
     * 商品阶级 1spu 2sku
     */
    @Column
    private long productLevel;
}
