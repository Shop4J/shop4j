package shop4j.services.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import shop4j.models.order.OrderDetail;
import shop4j.services.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/3 10:46
 * @Description:订单详情业务
 */
@Service
public interface OrderDetailService extends BaseService<OrderDetail>{
    /**
     * 通过spuIDS获取销量
     * @param spuIds
     * @return
     */
    Map<Long,Integer> sellCountBySPUIds(List<Long> spuIds);

    /**
     * 通过spuID获取销量
     * @param SPUID
     * @return 销量
     */
    int sellCountBySPUId(long spuId);

    void addList(List<OrderDetail> details);

    /**
     * 获得指定数量得动态热销品
     * @param size 数量
     * @return spuIds
     */
    List<Long> findMaxSellCount(int size);

    /**
     * 获得指定数量得动态热销品
     * @return spuIds
     */
    @Cacheable(value = "order",key = "'orderMaxSell'",sync = true)
    List<Long> findMaxSellCountCache();

    @CacheEvict(value="order",key="'orderMaxSell'")
    default void removeOrderMaxSellCache(){}
}
