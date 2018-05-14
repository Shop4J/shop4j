package shop4j.services.products.impl;

import base.util.collections.CollectionUtil;
import base.util.collections.opearator.CollectionsOperatorUtil;
import base.util.collections.parser.CollectionsParserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductKidMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.products.ProductKid;
import shop4j.services.products.ProductKidService;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
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

    @Override
    public List<ProductKid> getBySPUIds(List<Long> spuIds) {
        Example example = new Example(ProductKid.class);
        example.createCriteria().andIn("spuId",spuIds);
        return productKidMapper.selectByExample(example);
    }

    @Override
    public ProductKid getById(long id) {
        return productKidMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductKid> getBySPUId(long spuId) {
        Example example = new Example(ProductKid.class);
        example.createCriteria().andEqualTo("spuId",spuId);
        return productKidMapper.selectByExample(example);
    }

    @Override
    public List<ProductKid> getByIds(List<Long> ids) {
        Example example = new Example(ProductKid.class);
        example.createCriteria().andIn("id",ids);
        return productKidMapper.selectByExample(example);
    }

    @Override
    public List<ProductKid> maxSellCountSuggest2Month(int page,int size) {
        PageHelper.startPage(page,size);
        List<ProductKid> productKids = productKidMapper.maxSellCount();
        return productKids;
    }

    @Override
    public Map<Integer,List<ProductKid>> productDetailSuggestMaxSell(int size) {
        List<ProductKid> skus = maxSellCountSuggest2Month(1, size);
        Map<Integer, List<ProductKid>> countMap = CollectionsOperatorUtil.countGroup(3, skus);
        return countMap;
    }


    @Override
    public List<ProductKid> findMainSkuListBySpuIds(List<Long> spuIds) {
        Example example = new Example(ProductKid.class);
        example.createCriteria().andEqualTo("status", CommonDataStatus.OK.getStatus())
                .andIn("spuId",spuIds).andEqualTo("isMain",1);
        List<ProductKid> skus = productKidMapper.selectByExample(example);
        return skus;
    }

    @Override
    public Map<Long, ProductKid> findMainSkuMapBySpuIds(List<Long> spuIds) {
        List<ProductKid> skus = findMainSkuListBySpuIds(spuIds);
        return CollectionsParserUtil.collectFieldToMap(skus,ProductKid::getSpuId);
    }

    @Override
    public ProductKid findMainSkuBySpuId(long spuId) {
        Example example = new Example(ProductKid.class);
        example.createCriteria().andEqualTo("status", CommonDataStatus.OK.getStatus())
                .andEqualTo("spuId",spuId).andEqualTo("isMain",1);
        ProductKid sku = productKidMapper.selectOneByExample(example);
        return sku;
    }
}
