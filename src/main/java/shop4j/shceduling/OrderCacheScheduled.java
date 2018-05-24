package shop4j.shceduling;

import base.util.collections.CollectionUtil;
import base.util.collections.parser.CollectionsParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shop4j.models.products.ProductType;
import shop4j.services.order.OrderDetailService;
import shop4j.services.products.ProductService;
import shop4j.services.products.ProductTypeService;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/24 14:42
 * @Description:
 */
@Component
public class OrderCacheScheduled {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDetailService orderDetailService;

    @Scheduled(fixedDelay = 1000*60*30,initialDelay = 1000)
    public void OrderDetailMaxSellCacheRemove(){
        log.debug("维护订单最高售卖缓存开始！");
        orderDetailService.removeOrderMaxSellCache();
        orderDetailService.findMaxSellCountCache();
        log.debug("维护订单最高售卖缓存结束！");
    }
}
