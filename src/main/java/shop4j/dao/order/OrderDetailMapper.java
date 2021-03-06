package shop4j.dao.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.order.OrderDetail;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/3 10:40
 * @Description:订单明细
 */
@Component
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail>{
    /**
     * 计算某个SPU得有效销售数量
     * @param spuIds spu
     * @return key spuId,num生效
     */
    public List<OrderDetail> countBySPU(@Param("spuIds") List<Long> spuIds);

    /**
     * 获取某段时间到现在得最高动销品
     * @param beginTime
     * @return
     */
    List<Long> findMaxSellCount(LocalDate beginTime);
}
