package shop4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 13:54
 * @Description:测试数据初始化
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestInit {
    /**
     * 初始化spu
     */
    @Test
    public void initProduct(){
        ProductTest productTest = new ProductTest();
        productTest.testAdd();
    }

    public void initProductKid(){

    }
}
