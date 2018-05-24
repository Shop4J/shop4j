package shop4j.services.products.impl;

import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.models.products.ProductKid;
import shop4j.models.products.ProductParam;
import shop4j.models.products.ProductParamValue;
import shop4j.result.ProductParamResult;
import shop4j.result.SPUParamResult;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductParamService;
import shop4j.services.products.ProductParamValueService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private ProductKidService productKidService;
    @Override
    public ProductParamResult findParams(ProductKid currentSKU) {
        ProductParamResult productParamResult = new ProductParamResult();
        /**
         * 详情页下部商品明细参数组装
         */
        List<ProductParamValue> productSKUValues = productParamValueService.findDetailBySkuId(currentSKU.getId());//获得当前SKU 可再详情页展示得字段

        List<Long> paramIds = CollectionsParserUtil.collectFieldToList(productSKUValues, ProductParamValue::getParamId);
        List<ProductParam> params = productParamService.getByIds(paramIds);
        Map<String,List<String>> skuParamsMap = new LinkedHashMap<>();
        params.forEach(productParam -> {
            List<ProductParamValue> productValuesCurrentParams = productSKUValues.stream().filter(productParamValue -> productParamValue.getParamId() == productParam.getId()).collect(Collectors.toList());
            skuParamsMap.put(productParam.getMeaing(), CollectionsParserUtil.collectFieldToList(productValuesCurrentParams,ProductParamValue::getValue));
        });
        productParamResult.setSKUParams(skuParamsMap);
        /**
         * 详情页上部可选属性组装
         */
        List<ProductKid> skus = productKidService.getBySPUId(currentSKU.getSpuId());
        Map<Long, ProductKid> skuMap = CollectionsParserUtil.collectFieldToMap(skus, ProductKid::getId);
        List<ProductParam> spuSeeParams = params.stream().filter(productParam -> productParam.getIsSpuSee() == 1).collect(Collectors.toList());
        List<ProductParamValue> spuProductParamValues = productParamValueService.findsByParamIds(CollectionsParserUtil.collectFieldToList(spuSeeParams, ProductParam::getId),currentSKU.getId());
        Map<String,List<SPUParamResult>> spuParamsMap = new LinkedHashMap<>();
        spuSeeParams.forEach(spuSeeParam->{
            List<ProductParamValue> skuProductValuesCurrentParamsValues = spuProductParamValues.stream().filter(productParamValue -> productParamValue.getParamId() == spuSeeParam.getId()).collect(Collectors.toList());
            List<SPUParamResult> spuParamResults = new ArrayList<>();
            skuProductValuesCurrentParamsValues.forEach(skuProductValuesCurrentParamsValue -> {
                SPUParamResult spuParamResult = new SPUParamResult();
                spuParamResult.setValue(skuProductValuesCurrentParamsValue.getValue());
                spuParamResult.setCurrent(productSKUValues.stream().anyMatch(productValue->productValue.getId()==skuProductValuesCurrentParamsValue.getId()));
                ProductKid sku = skuMap.get(skuProductValuesCurrentParamsValue.getProductId());
                spuParamResult.setEnough(sku.getNum()>0);
                spuParamResult.setUrl(new StringBuilder().append("?skuId=").append(sku.getId()).append("&spuId=").append(sku.getSpuId()).toString());
                spuParamResults.add(spuParamResult);
            });
            spuParamsMap.put(spuSeeParam.getMeaing(),spuParamResults);
        });
        productParamResult.setSPUParams(spuParamsMap);
        return productParamResult;
    }
}
