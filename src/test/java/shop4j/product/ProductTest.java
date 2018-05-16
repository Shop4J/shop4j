package shop4j.product;

import base.util.random.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.models.products.Product;
import shop4j.services.products.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 14:11
 * @Description:产品业务测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest {
    @Autowired
    private ProductService productService;
    @Test
    public void testAdd(){
        List<Product> productList = new ArrayList<>();
        for(int i=1 ;i<=5000;i++) {
            Product product = new Product();
            product.setName(i+" 春季新款光泽裤修身显瘦打底裤 弹力大码九分小脚裤子女");
            product.setDetail("拉里萨说的啥的货物的核武器和动物i哦亲");
            product.setType(RandomUtil.rangeRandom(11,57));
            product.setAddTime(new Date());
            product.setAddOperator(1);
            product.setStatus(1);
            productList.add(product);
            product.setShowPrice(BigDecimal.valueOf(new Random().nextInt(100)));
        }
        productService.addList(productList);
    }
}
