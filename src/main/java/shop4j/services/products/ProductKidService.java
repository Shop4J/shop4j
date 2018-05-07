package shop4j.services.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}
