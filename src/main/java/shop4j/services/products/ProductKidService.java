package shop4j.services.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop4j.models.products.Product;
import shop4j.models.products.ProductKid;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 11:13
 * @Description:SKU业务
 */
@Service
public interface ProductKidService {
    /**
     * 通过spuIds计算库存
     * @param spuIds
     * @return key是spuId,value是数量
     */
    Map<Long,Integer> countStoreBySpuIds(List<Long> spuIds);

    /**
     * 添加sku
     * @param kidProducts sku集合
     */
    void addList(List<ProductKid> kidProducts);

    /**
     * 通过SPUID集合获取SKU
     * @return 子商品集合
     */
    List<ProductKid> getBySPUIds(List<Long> spuIds);

    /**
     * 通过skuId获取sku
     * @param id skuI
     * @return sku
     */
    ProductKid getById(long id);

    /**
     * 通过父类spu查询下面所有产品
     * @param spuId
     * @return sku集合
     */
    List<ProductKid> getBySPUId(long spuId);
    /**
     * 通过skuId集合获取sku
     * @param ids
     * @return sku集合
     */
    List<ProductKid> getByIds(List<Long> ids);

    /**
     * 2个月内得最高热销
     * @return 8个热销商品
     */
    List<ProductKid> maxSellCountSuggest2Month();
}
