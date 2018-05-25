package shop4j.result;

import lombok.Data;

/**
 * @Author: weixuedong
 * @Date: 2018/5/21 17:25
 * @Description: SPU参数返回实体
 */
@Data
public class SPUParamResult {
    /**
     * 值
     */
    private String value;
    /**
     * 是否与SKU一致
     */
    private boolean isCurrent;

    /**
     * 对应SKU-URL
     */
    private String url;

    /**
     * 库存不足或者无此商品返回 false
     */
    private boolean isCanUse;

    private long paramId;

}
