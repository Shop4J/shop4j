package shop4j.services.products.impl;

import base.util.collections.CollectionUtil;
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
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductParamService;
import shop4j.services.products.ProductParamValueService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Map<Long, ProductParam> spuSeeParamsMap = CollectionsParserUtil.collectFieldToMap(spuSeeParams, ProductParam::getId);
        List<ProductParamValue> spuProductParamValues = productParamValueService.findsByParamIds(CollectionsParserUtil.collectFieldToList(spuSeeParams, ProductParam::getId),CollectionsParserUtil.collectFieldToList(skus,ProductKid::getId));
        List<ProductParamValue> canUseSpuParamValues = CollectionsOperatorUtil.distinct(spuProductParamValues, ProductParamValue::getValue);
        Map<String,List<SPUParamResult>> spuParamsMap = new LinkedHashMap<>();
        //当前选中得参数值map
        Map<Long, ProductParamValue> skuParamValueMap = CollectionsParserUtil.collectFieldToMap(productSKUValues, ProductParamValue::getParamId);

        /**
         * 处理当前选中属性和页面需要展示的各种分类
         */
        canUseSpuParamValues.forEach(canUseSpuParamValue->{
            SPUParamResult spuParamResult =new SPUParamResult();
            spuParamResult.setValue(canUseSpuParamValue.getValue());
            spuParamResult.setParamId(canUseSpuParamValue.getParamId());
            //处理当前选中得
            ProductParamValue skuParamValue = skuParamValueMap.get(canUseSpuParamValue.getParamId());
            if(skuParamValue.getValue().equals(canUseSpuParamValue.getValue())){
                spuParamResult.setCurrent(true);
                spuParamResult.setCanUse(currentSKU.getNum()>0);
                spuParamResult.setUrl("?skuId="+currentSKU.getId()+"&spuId="+currentSKU.getSpuId());
            }

            ProductParam spuSeeParam = spuSeeParamsMap.get(canUseSpuParamValue.getParamId());
            List<SPUParamResult> spuParams = spuParamsMap.get(spuSeeParam.getMeaing());
            if(CollectionUtil.isEmpty(spuParams)){
                spuParams = new ArrayList<>();
                spuParams.add(spuParamResult);
                spuParamsMap.put(spuSeeParam.getMeaing(),spuParams);
            }else {
                spuParams.add(spuParamResult);
            }
        });
        dealOtherValue(spuParamsMap,productSKUValues,spuProductParamValues,skuMap);
        dealOtherDefault(spuParamsMap,skuMap,spuProductParamValues);
        productParamResult.setSPUParams(spuParamsMap);
        return productParamResult;
    }

    /**
     * 处理第一分类层必须量防止没货全不可点击问题
     * @param spuParamsMap
     */
    private void dealOtherDefault(Map<String,List<SPUParamResult>> spuParamsMap,Map<Long, ProductKid> skuMap,List<ProductParamValue> spuProductParamValues){
        for(Map.Entry<String,List<SPUParamResult>> entry:spuParamsMap.entrySet()){
            List<SPUParamResult> firstSpuParamResults = entry.getValue();
            firstSpuParamResults.forEach(firstSpuParamResult->{
                if(firstSpuParamResult.isCanUse()==false){//首层如果不可用
                    List<ProductParamValue> skuSelectDefaultValues = spuProductParamValues.stream().filter(spuProductParamValue -> spuProductParamValue.getParamId() == firstSpuParamResult.getParamId()&&spuProductParamValue.getValue().equals(firstSpuParamResult.getValue()) && skuMap.get(spuProductParamValue.getProductId()).getNum() > 0).collect(Collectors.toList());
                    if(CollectionUtil.isNotEmpty(skuSelectDefaultValues)){
                        ProductParamValue skuSelectDefaultValue = skuSelectDefaultValues.get(0);
                        ProductKid currentSKU = skuMap.get(skuSelectDefaultValue.getProductId());
                        firstSpuParamResult.setCanUse(true);
                        firstSpuParamResult.setUrl("?skuId="+currentSKU.getId()+"&spuId="+currentSKU.getSpuId());
                    }
                }
            });
            return;
        }
    }
    /**
     * 专门处理其他可选分类的URL和是否有这产品
     * @param spuParamsMap 已经维护过的可选属性和当前属性选中情况
     * @param currentSku 当前SKU属性值
     * @param spuProductParamValues 所有SKU属性值
     * @param skuMap 当前SPU下的所有SKU
     */
    private void dealOtherValue(Map<String,List<SPUParamResult>> spuParamsMap,List<ProductParamValue> currentSku,List<ProductParamValue> spuProductParamValues,Map<Long, ProductKid> skuMap){
        List<SPUParamResult> spuParamResults = new ArrayList<>();
        spuParamsMap.forEach((meaing,results)->{
            spuParamResults.addAll(results);
        });
        Set<Long> paramIds = CollectionsParserUtil.collectFieldToSet(spuParamResults, SPUParamResult::getParamId);

        spuParamResults.forEach(spuParamResult -> {

            if(spuParamResult.isCurrent()==false){
                /**
                 * 找到其他与当前维护属性相同的值的
                 */
                List<ProductParamValue> otherSameCurrenParamSKUS = spuProductParamValues.stream().filter(productParamValue -> productParamValue.getValue().equals(spuParamResult.getValue()) && productParamValue.getParamId()==spuParamResult.getParamId()).collect(Collectors.toList());
                List<ProductParamValue> spuProductPararmValues2 = spuProductParamValues.stream().filter(productParamValue -> CollectionsParserUtil.collectFieldToList(otherSameCurrenParamSKUS, ProductParamValue::getProductId).contains(productParamValue.getProductId())).collect(Collectors.toList());
                /**
                 * 其他属性编号
                 */
                List<Long> otherParamsIds = paramIds.stream().filter(paramId -> paramId != spuParamResult.getParamId()).collect(Collectors.toList());
                long skuId = findSame(spuProductPararmValues2,currentSku,otherParamsIds);
                /**
                 * 判断当前选中SKU属性与当前维护属性的区别
                 */
                if(skuId!=0){
                    ProductKid sku = skuMap.get(skuId);
                    if(sku.getNum()>0){
                        spuParamResult.setCanUse(true);
                        spuParamResult.setUrl("?skuId="+sku.getId()+"&spuId="+sku.getSpuId());
                    }
                }
            }
        });
    }

    /**
     * 获得指定属性IDS
     * @param spuProductParamValues 所有属性值
     * @param currentSku 当前SKU属性值
     * @param otherParamsIds 指定的属性ID
     * @return productId
     */
    public long findSame(List<ProductParamValue> spuProductParamValues,List<ProductParamValue> currentSku,List<Long> otherParamsIds){
        /**
         *  todo  找到第一次个参数与第二个参数 再指定key的情况下value完全相同的
         */
        Map<Long,Integer> map = new HashMap<>();//key是productId value是相同的次数
        for(int i=0;i<spuProductParamValues.size();i++){
            ProductParamValue spuParamValue = spuProductParamValues.get(i);
            for(int p=0 ;p<otherParamsIds.size();p++){
                Long otherParamsId = otherParamsIds.get(p);
                ProductParamValue currentSkuParamValue = currentSku.stream().filter(currentSkuOneParamValue->currentSkuOneParamValue.getParamId()==otherParamsId).collect(Collectors.toList()).get(0);
                if(currentSkuParamValue.getValue().equals(spuParamValue.getValue())){
                    Integer time =map.get(spuParamValue.getProductId());
                    if(Objects.isNull(time)){
                        map.put(spuParamValue.getProductId(),1);
                    }else{
                        map.put(spuParamValue.getProductId(),++time);
                    }
                }
            }
        }
        for(Map.Entry<Long,Integer> entry :map.entrySet()){
            Long productId = entry.getKey();
            Integer time = entry.getValue();
            if(time.equals(otherParamsIds.size())){
                return productId;
            }
        }
        return 0;
    }
}
