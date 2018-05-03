package shop4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.models.order.OrderDetail;
import shop4j.services.order.OrderDetailService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/3 11:49
 * @Description:订单详情测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailTest {

    @Autowired
    private OrderDetailService orderDetailService;
    @Test
    public void testAdd(){
        List<OrderDetail> details= new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setNum(2);
        orderDetail.setOrderId(13);
        orderDetail.setPriceTotal(BigDecimal.valueOf(500));
        orderDetail.setSkuId(30);
        orderDetail.setUnitPrice(BigDecimal.valueOf(250));
        orderDetail.setSpuId(75016);
        orderDetail.setAddOperator(1);
        orderDetail.setAddTime(new Date());
        orderDetail.setStatus(CommonDataStatus.OK.getStatus());
        details.add(orderDetail);
        orderDetailService.addList(details);
    }
}
