package shop4j.vo.product;

import lombok.Data;

/**
 * @Author: weixuedong
 * @Date: 2018/5/22 18:43
 * @Description:商品图片地址
 */
@Data
public class ProductImageVO {
    /**
     * 地址
     */
    private String url;
    /**
     * 地址
     */
    private int sort;

    /**
     * 图片类型 2是SKU图 3是明细图
     */
    private int type;

}
