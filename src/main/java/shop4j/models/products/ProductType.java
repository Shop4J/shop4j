package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:11
 * @Description:产品类型
 */
@Table(name ="db_products.tbl_product_type")
@Data
public class ProductType extends BaseModel {

    /**
     * 父类型
     */
    private long parentId;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 图片地址 用于首页 要求图形必须是300*560
     */
    private String imageUrl;
}
