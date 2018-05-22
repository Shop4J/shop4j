package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductParamMapper;
import shop4j.dao.products.ProductParamValueMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.products.ProductParamValue;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductParamValueService;
import tk.mybatis.mapper.entity.Example;

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
    public List<ProductParamValue> findBySkuId(long skuId) {
        instanceCriteria().andEqualTo("productId", skuId).andEqualTo("status", CommonDataStatus.OK.getStatus());
        return productParamValueMapper.selectByExample(exampleThreadLocal.get());
    }

    @Override
    public List<ProductParamValue> findDetailBySkuId(long skuId) {
        return productParamValueMapper.findDetailBySkuId(skuId);
    }
}
