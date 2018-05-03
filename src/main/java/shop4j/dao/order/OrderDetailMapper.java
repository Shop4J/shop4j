package shop4j.dao.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.order.OrderDetail;

import java.util.List;
import java.util.Map;

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
}
