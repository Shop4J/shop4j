package shop4j.services.order;

import org.springframework.stereotype.Service;
import shop4j.models.order.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/3 10:46
 * @Description:订单详情业务
 */
@Service
public interface OrderDetailService {
    /**
     * 通过spuIDS获取销量
     * @param spuIds
     * @return
     */
    Map<Long,Integer> sellCountBySPUIds(List<Long> spuIds);

    void addList(List<OrderDetail> details);

    /**
     * 获得指定数量得动态热销品
     * @param size 数量
     * @return spuIds
     */
    List<Long> findMaxSellCount(int size);
}
