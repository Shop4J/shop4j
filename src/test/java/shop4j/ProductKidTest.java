package shop4j;

import base.util.collections.parser.CollectionsParserUtil;
import base.util.random.RandomUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shop4j.models.products.Color;
import shop4j.models.products.ProductKid;
import shop4j.models.products.Product;
import shop4j.services.products.ColorService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductService;
import shop4j.vo.SearchProductVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
            kidProduct.setColor(RandomUtil.rangeRandom(colorIds));
            kidProduct.setNum(RandomUtil.rangeRandom(1,50));
            kidProduct.setPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(40,300)));
            kidProduct.setSpuId(product.getId());
            kidProduct.setAddOperator(1);
            kidProduct.setAddTime(new Date());
            kidProduct.setStatus(1);
            kidProduct.setSize(RandomUtil.rangeRandom(Arrays.asList("L","M","48","51","121","170","XL")));
            productKids.add(kidProduct);
        });
        productKidService.addList(productKids);
    }
}
