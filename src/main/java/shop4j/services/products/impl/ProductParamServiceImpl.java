package shop4j.services.products.impl;

import base.util.collections.opearator.CollectionsOperatorUtil;
import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.models.products.ProductKid;
import shop4j.models.products.ProductParam;
import shop4j.models.products.ProductParamValue;
import shop4j.result.ProductParamResult;
import shop4j.result.SPUParamResult;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductParamService;
import shop4j.services.products.ProductParamValueService;
import shop4j.services.products.ProductTypeParamService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:12
 * @Description:商品参数业务
 */
@Service
public class ProductParamServiceImpl extends BaseServiceImpl<ProductParam> implements ProductParamService{
    @Autowired
    private ProductParamValueService productParamValueService ;
    @Autowired
    private ProductParamService productParamService;

    @Override
    public ProductParamResult findParams(ProductKid currentSKU) {
        ProductParamResult productParamResult = new ProductParamResult();
        List<ProductParamValue> productValues = productParamValueService.findDetailBySkuId(currentSKU.getId());//获得当前SKU 可再详情页展示得字段
        List<Long> paramIds = CollectionsParserUtil.collectFieldToList(productValues, ProductParamValue::getParamId);
        List<ProductParam> params = productParamService.getByIds(paramIds);
        Map<String,List<String>> skuParamsMap = new LinkedHashMap<>();
        params.forEach(productParam -> {
            List<ProductParamValue> productValuesCurrentParams = productValues.stream().filter(productParamValue -> productParamValue.getParamId() == productParam.getId()).collect(Collectors.toList());
            skuParamsMap.put(productParam.getMeaing(), CollectionsParserUtil.collectFieldToList(productValuesCurrentParams,ProductParamValue::getValue));
        });
        productParamResult.setSKUParams(skuParamsMap);
        Map<String,List<SPUParamResult>> spuParamsMap = new LinkedHashMap<>();
        
        productParamResult.setSPUParams(spuParamsMap);
        return productParamResult;
    }
}
