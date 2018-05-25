package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductParamValueMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.products.ProductParamValue;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductParamValueService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/17 18:38
 * @Description:商品属性值
 */
@Service
public class ProductParamValueServiceImpl extends BaseServiceImpl<ProductParamValue> implements ProductParamValueService {
    @Autowired
    private ProductParamValueMapper productParamValueMapper;

    @Override
    public List<ProductParamValue> findDetailBySkuId(long skuId) {
        return productParamValueMapper.findDetailBySkuId(skuId);
    }

    @Override
    public List<ProductParamValue> findsByParamIds(List<Long> paramIds,List<Long> skuIds) {
        instanceCriteria().andIn("paramId",paramIds).andEqualTo("status",CommonDataStatus.OK.getStatus()).andIn("productId",skuIds);
        return productParamValueMapper.selectByExample(exampleThreadLocal.get());
    }
}
