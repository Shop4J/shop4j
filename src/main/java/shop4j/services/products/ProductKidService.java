package shop4j.services.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop4j.models.products.Product;
import shop4j.models.products.ProductKid;
import shop4j.services.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 11:13
 * @Description:SKU业务
 */
@Service
public interface ProductKidService extends BaseService<ProductKid>{
    /**
     * 通过spuIds计算库存
     * @param spuIds
     * @return key是spuId,value是数量
     */
    Map<Long,Integer> countStoreBySpuIds(List<Long> spuIds);

    /**
     * 通过SPUID集合获取SKU
     * @return 子商品集合
     */
    List<ProductKid> getBySPUIds(List<Long> spuIds);


    /**
     * 通过父类spu查询下面所有产品
     * @param spuId
     * @return sku集合
     */
    List<ProductKid> getBySPUId(long spuId);

    /**
     * 2个月内得最高热销
     * @return 8个热销商品
     */
    List<ProductKid> maxSellCountSuggest2Month(int page,int size);

    /**
     * 商品详情页使用得热销品
     * @param size 需要得数量
     * @return 分组后得子品
     */
    Map<Integer,List<ProductKid>> productDetailSuggestMaxSell(int size);


    /**
     * 获得多个SPU得主品
     * @param spuIds
     * @return map key是spu id value是主品
     */
    public Map<Long,ProductKid> findMainSkuMapBySpuIds(List<Long> spuIds);

    /**
     * 获得多个SPU得主品
     * @param spuIds
     * @return spu得主品集合
     */
    public List<ProductKid> findMainSkuListBySpuIds(List<Long> spuIds);

    /**
     * 获得单个SPU得主品
     * @param spuId
     * @return spu得主品
     */
    public ProductKid findMainSkuBySpuId(long spuId);

    /**
     * 获得单个SPU得主品,如果skuId!=0则获取默认得，如果有则获取当前SKU
     * @param spuId spuId
     * @param skuId skuId
     * @return spu得主品
     */
    public ProductKid findCurrentSku(long spuId,long skuId);
}
