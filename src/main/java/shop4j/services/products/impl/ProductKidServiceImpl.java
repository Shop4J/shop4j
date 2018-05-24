package shop4j.services.products.impl;

import base.util.collections.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductKidMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.products.ProductKid;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductKidService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 11:19
 * @Description:sku业务实现
 */
@Service
public class ProductKidServiceImpl extends BaseServiceImpl<ProductKid> implements ProductKidService {
    @Autowired
    private ProductKidMapper productKidMapper;
    @Override
    public Map<Long, Integer> countStoreBySpuIds(List<Long> spuIds) {
        Map<Long, Integer> storeCountMap = new HashMap<>();

        if(CollectionUtil.isNotEmpty(spuIds)) {

            List<ProductKid> counts = productKidMapper.countStoreBySPU(new HashSet<>(spuIds));

            if(CollectionUtil.isNotEmpty(counts)){
                counts.forEach(count-> storeCountMap.put(count.getSpuId(),count.getNum()));
            }
            spuIds.forEach(spuId->{
                if(storeCountMap.get(spuId)==null){
                    storeCountMap.put(spuId,0);
                }
            });
        }
        return storeCountMap;
    }

    @Override
    public List<ProductKid> getBySPUIds(List<Long> spuIds) {
        instanceCriteria().andIn("spuId",spuIds);
        return productKidMapper.selectByExample(exampleThreadLocal.get());
    }

    @Override
    public List<ProductKid> getBySPUId(long spuId) {
        instanceCriteria().andEqualTo("spuId",spuId);
        return productKidMapper.selectByExample(exampleThreadLocal.get());
    }

    @Override
    public List<ProductKid> findMainSkuListBySpuIds(List<Long> spuIds) {
        instanceCriteria().andEqualTo("status", CommonDataStatus.OK.getStatus())
                .andIn("spuId",spuIds).andEqualTo("isMain",1);
        List<ProductKid> skus = productKidMapper.selectByExample(exampleThreadLocal.get());
        return skus;
    }

    @Override
    public ProductKid findMainSkuBySpuId(long spuId) {
       instanceCriteria().andEqualTo("status", CommonDataStatus.OK.getStatus())
                .andEqualTo("spuId",spuId).andEqualTo("isMain",1);
        ProductKid sku = productKidMapper.selectOneByExample(exampleThreadLocal.get());
        return sku;
    }

    @Override
    public ProductKid findCurrentSku(long spuId, long skuId) {
        if(skuId ==0){
            return findMainSkuBySpuId(spuId);
        }
        return findById(skuId);
    }
}
