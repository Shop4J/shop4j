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
     * 通过sku和spuID获得
     * @param skuId SKUID
     * @param spuId SPUID
     * @return 获取到得属性值
     */
    public List<ProductParamValue> findBySkuAndSpu(long skuId,long spuId);
}
