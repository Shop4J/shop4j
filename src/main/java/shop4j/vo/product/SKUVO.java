package shop4j.vo.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/22 16:20
 * @Description:sku封装类
 */
@Data
public class SKUVO implements Serializable{
    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private int num;

    /**
     *  是否未主品
     */
    private int isMain;

    /**
     * 商品参数与值
     */
    private List<ProductParamVO> productParamBeanList;

    /**
     * 商品图片
     */
    private List<ProductImageVO> productImageBeans;
}
