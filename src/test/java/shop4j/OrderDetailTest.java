package shop4j;

import base.util.collections.parser.CollectionsParserUtil;
import base.util.random.RandomUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.models.order.OrderDetail;
import shop4j.models.products.Product;
import shop4j.models.products.ProductKid;
import shop4j.services.order.OrderDetailService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductService;
import shop4j.vo.SearchProductVO;

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
    @Autowired
    private ProductKidService productKidService;
    @Autowired
    private ProductService productService;
    @Test
    public void testAdd(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        SearchProductVO searchProductVO = new SearchProductVO();
        searchProductVO.setSize(Integer.MAX_VALUE);
        searchProductVO.setPage(1);
        PageInfo<Product> productsPage = productService.findBySearchVO(searchProductVO);
        List<Product> products = productsPage.getList();
        List<Long> spuIds = CollectionsParserUtil.collectFieldToList(products, Product::getId);
        List<ProductKid> productKids = productKidService.getBySPUIds(spuIds);
        productKids.forEach(productKid -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setStatus(CommonDataStatus.OK.getStatus());
            orderDetail.setAddTime(new Date());
            orderDetail.setAddOperator(1);
            orderDetail.setSpuId(productKid.getSpuId());
            orderDetail.setUnitPrice(productKid.getPrice());
            orderDetail.setNum(RandomUtil.rangeRandom(1,20));
            orderDetail.setPriceTotal(orderDetail.getUnitPrice().multiply(BigDecimal.valueOf(orderDetail.getNum())));
            orderDetail.setSkuId(productKid.getId());
            orderDetail.setOrderId(RandomUtil.rangeRandom(1,10000));
            orderDetails.add(orderDetail);
        });
        orderDetailService.addList(orderDetails);

    }
}
