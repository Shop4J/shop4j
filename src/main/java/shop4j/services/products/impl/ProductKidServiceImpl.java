package shop4j.services.products.impl;

import base.util.collections.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductKidMapper;
import shop4j.models.products.ProductKid;
import shop4j.services.products.ProductKidService;

import java.util.*;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 11:19
 * @Description:sku业务实现
 */
@Service
public class ProductKidServiceImpl implements ProductKidService {
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
    public void addList(List<ProductKid> kidProducts) {
        productKidMapper.insertList(kidProducts);
    }
}
