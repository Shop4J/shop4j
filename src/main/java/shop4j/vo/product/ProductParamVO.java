package shop4j.vo.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: weixuedong
 * @Date: 2018/5/22 16:52
 * @Description:商品参数值
 */
@Data
public class ProductParamVO implements Serializable{
    private long paramId;

    private String value;
}
