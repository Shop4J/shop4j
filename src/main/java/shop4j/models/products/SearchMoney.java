package shop4j.models.products;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 14:04
 * @Description: 金钱搜索
 */
@Table(name = "db_products.tbl_search_money")
@Data
public class SearchMoney extends BaseModel{

    private String name;

    /**
     * 开始 -1不限
     */
    private int begin;

    /**
     * 结束 -1不限
     */
    private int end;

}
