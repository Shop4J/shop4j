package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/4/26 19:17
 * @Description:
 */
@Table(name = "db_products.tbl_color")
@Data
public class Color extends BaseModel {
    /**
     * 颜色名称
     */
    private String name;
}
