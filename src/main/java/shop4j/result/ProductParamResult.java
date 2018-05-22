package shop4j.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/21 17:00
 * @Description: 页面需要得参数
 */
@Data
public class ProductParamResult implements Serializable{
    /**
     * SKU属性和属性值
     */
    Map<String,List<String>> SKUParams;

    /**
     * spu属性和属性值
     */
    Map<String,List<SPUParamResult>> SPUParams;


}
