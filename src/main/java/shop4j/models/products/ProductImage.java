package shop4j.models.products;

import lombok.Data;
import shop4j.enums.ProductImageTypeEnum;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:13
 * @Description:商品图片
 */
@Table(name ="db_products.tbl_product_image")
@Data
public class ProductImage extends BaseModel{
    /**
     * 图片地址
     */
    @Column
    private String url;

    /**
     * 产品编号
     */
    @Column
    private long productId;

    /**
     * @see  ProductImageTypeEnum
     */
    @Column
    private int type;

    /**
     * 从1开始 1位置为主图
     */
    @Column
    private int sort;
}
