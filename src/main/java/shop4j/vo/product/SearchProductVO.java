package shop4j.vo.product;

import lombok.Data;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/28 18:37
 * @Description:产品搜索VO
 */
@Data
public class SearchProductVO {
    /**
     * 关键词
     */
    private String searchText;

    /**
     * 产品子类型ID
     * @see shop4j.models.products.ProductType
     */
    private Long productKidType;

    /**
     * 复选颜色
     */
    private List<Long> colors;
    /**
     * 复选产品类型（主类型）
     */
    private List<Long> productTypes;
    /**
     * 年龄
     */
    private List<Long> yearOlds;
    /**
     * 金额
     */
    private List<Long> moneys;

    /**
     * 金额
     */
    private Integer  moneyBegin;

    /**
     * 金额
     */
    private Integer  moneyEnd;
    /**
     * 默认当前页
     */
    private int page = 1;

    /**
     * 默认分页数量
     */
    private int size =20;

}
