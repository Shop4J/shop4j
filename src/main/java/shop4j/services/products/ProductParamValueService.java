package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductParamValue;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/17 18:36
 * @Description:商品属性值
 */
@Service
public interface ProductParamValueService extends BaseService<ProductParamValue>{

    /**
     * 通过SKUID的查看
     * @param skuId SKUID
     * @return 获取到得属性值
     */
    List<ProductParamValue> findDetailBySkuId(long skuId);

    /**
     * 通过参数ID查看参数值
     * @param paramIds 参数ID
     * @return 获取到得属性值
     */
    List<ProductParamValue> findsByParamIds(List<Long> paramIds,List<Long> skuIds);
}
