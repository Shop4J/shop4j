package shop4j.vo.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/22 16:13
 * @Description: 完整商品添加
 */
@Data
public class ProductVO implements Serializable{
    /**
     * SPU名
     */
    private String name;

    /**
     * @see shop4j.models.products.ProductType
     */
    private long productType;

    /**
     * 商品描述
     */
    private String detail;

    /**
     * 商品展示价
     */
    private BigDecimal showPrice;

    /**
     * SKU明细
     */
    private List<SKUVO> SKUVOList;

}
