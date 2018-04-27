package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 11:14
 * @Description:适用年龄
 */
@Table(name = "db_products.tbl_year_old")
@Data
public class YearOld extends BaseModel{
    /**
     * 范围名称
     */
    private String name;

    /**
     * 开始
     */
    private int begin;

    /**
     * 结束
     */
    private int end;

    /**
     * 1是单位月 2是单位年
     */
    private int type;

}
