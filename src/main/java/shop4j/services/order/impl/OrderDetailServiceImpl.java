package shop4j.services.order.impl;

import base.util.collections.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.order.OrderDetailMapper;
import shop4j.models.order.OrderDetail;
import shop4j.services.order.OrderDetailService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: weixuedong
 * @Date: 2018/5/3 10:52
 * @Description:订单详情实现
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public Map<Long, Integer> sellCountBySPUIds(List<Long> spuIds) {
        Map<Long,Integer> sellCountMap = new HashMap<>();

        List<OrderDetail> orderDetails = orderDetailMapper.countBySPU(spuIds);

        if(CollectionUtil.isNotEmpty(orderDetails)){
            orderDetails.forEach(orderDetail -> sellCountMap.put(orderDetail.getSpuId(),orderDetail.getNum()));
        }

        spuIds.forEach(spuId->{
            if(Objects.isNull(sellCountMap.get(spuId))){
                sellCountMap.put(spuId,0);
            }
        });
        return sellCountMap;
    }

    @Override
    public void addList(List<OrderDetail> details) {
        orderDetailMapper.insertList(details);
    }

    @Override
    public List<Long> findMaxSellCount(int size) {
        List<Long> spuIds = findMaxSellCountCache();
        return spuIds.stream().limit(size).collect(Collectors.toList());
    }

    @Override
    public List<Long> findMaxSellCountCache() {
         PageHelper.startPage(1,15);
        List<Long> spuIds = orderDetailMapper.findMaxSellCount(LocalDate.now().minusMonths(2));
        return spuIds;
    }

}
