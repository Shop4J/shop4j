package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:00
 * @Description:产品详细信息
 */
@Table(name = "db_products.tbl_product_param")
@Data
public class ProductParam extends BaseModel{
    /**
     * 字段名
     */
    @Column
    private String name;

    /**
     * 含义
     */
    @Column
    private String meaing;

    /**
     * 1是spu应用属性 2是sku应用属性
     */
    @Column
    private int type;
}
