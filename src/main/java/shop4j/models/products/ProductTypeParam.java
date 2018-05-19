package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:41
 * @Description:
 */
@Data
@Table(name = "db_products.tbl_product_type_param")
public class ProductTypeParam extends BaseModel{
    /**
     * 类型编号
     */
    @Column
    private long typeId;

    /**
     *  参数编号
     */
    @Column
    private long paramId;

    /**
     * 排序
     */
    @Column
    private int sort;
}
