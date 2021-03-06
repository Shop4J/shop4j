package shop4j.product;

import base.util.collections.parser.CollectionsParserUtil;
import base.util.random.RandomUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.models.products.Color;
import shop4j.models.products.Product;
import shop4j.models.products.ProductKid;
import shop4j.services.products.ColorService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductService;
import shop4j.vo.product.SearchProductVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 13:59
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductKidTest {
    @Autowired
    private ProductKidService productKidService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ColorService colorService;

    @Test
    public void testAdd(){
        SearchProductVO searchVO=new SearchProductVO();
        searchVO.setPage(1);
        searchVO.setSize(Integer.MAX_VALUE);
        PageInfo<Product> products = productService.findBySearchVO(searchVO);
        List<ProductKid> productKids = new ArrayList<>();
        List<Color> colors = colorService.findAll();
        List<Long> colorIds = CollectionsParserUtil.collectFieldToList(colors, Color::getId);
        products.getList().forEach(product -> {
            ProductKid kidProduct = new ProductKid();
            kidProduct.setNum(RandomUtil.rangeRandom(1,50));
            kidProduct.setPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(40,300)));
            kidProduct.setSpuId(product.getId());
            kidProduct.setAddOperator(1);
            kidProduct.setAddTime(new Date());
            kidProduct.setStatus(1);
            productKids.add(kidProduct);
        });
        productKidService.addList(productKids);
    }

    @Test
    public void testUpdate(){
        List<ProductKid> products = productKidService.findAll();
        Map<Long, List<ProductKid>> productMap = CollectionsParserUtil.collectFieldToMapList(products, ProductKid::getSpuId);
        productMap.forEach((key,value)->{
            ProductKid productKid = value.get(0);
            productKid.setIsMain(1);
            productKidService.update(productKid);
        });
    }

}
