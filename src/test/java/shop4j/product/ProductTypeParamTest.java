package shop4j.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.models.BaseModel;
import shop4j.models.products.ProductTypeParam;
import shop4j.services.products.ProductTypeParamService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/15 14:34
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTypeParamTest {
    @Autowired
    private ProductTypeParamService productTypeParamService;

    @Test
    public void test(){
        List<ProductTypeParam> list = new ArrayList<>();
        ProductTypeParam productTypeParam = new ProductTypeParam();
        productTypeParam.setTypeId(11);
        productTypeParam.setParamId(18);
        list.add(productTypeParam);
        ProductTypeParam productTypeParam1 = new ProductTypeParam();
        productTypeParam1.setTypeId(11);
        productTypeParam1.setParamId(19);
        list.add(productTypeParam1);
        ProductTypeParam productTypeParam2 = new ProductTypeParam();
        productTypeParam2.setTypeId(11);
        productTypeParam2.setParamId(20);
        list.add(productTypeParam2);
        ProductTypeParam productTypeParam3 = new ProductTypeParam();
        productTypeParam3.setTypeId(11);
        productTypeParam3.setParamId(21);
        list.add(productTypeParam3);
        ProductTypeParam productTypeParam4 = new ProductTypeParam();
        productTypeParam4.setTypeId(11);
        productTypeParam4.setParamId(22);
        list.add(productTypeParam4);
        ProductTypeParam productTypeParam5 = new ProductTypeParam();
        productTypeParam5.setTypeId(11);
        productTypeParam5.setParamId(23);
        list.add(productTypeParam5);
        ProductTypeParam productTypeParam6 = new ProductTypeParam();
        productTypeParam6.setTypeId(11);
        productTypeParam6.setParamId(24);
        list.add(productTypeParam6);
        ProductTypeParam productTypeParam7 = new ProductTypeParam();
        productTypeParam7.setTypeId(11);
        productTypeParam7.setParamId(25);
        list.add(productTypeParam7);
        ProductTypeParam productTypeParam8 = new ProductTypeParam();
        productTypeParam8.setTypeId(11);
        productTypeParam8.setParamId(26);
        list.add(productTypeParam8);
        ProductTypeParam productTypeParam9 = new ProductTypeParam();
        productTypeParam9.setTypeId(11);
        productTypeParam9.setParamId(27);
        list.add(productTypeParam9);
        ProductTypeParam productTypeParam10 = new ProductTypeParam();
        productTypeParam10.setTypeId(11);
        productTypeParam10.setParamId(31);
        list.add(productTypeParam10);
        ProductTypeParam productTypeParam11 = new ProductTypeParam();
        productTypeParam11.setTypeId(11);
        productTypeParam11.setParamId(32);
        list.add(productTypeParam11);
        ProductTypeParam productTypeParam12 = new ProductTypeParam();
        productTypeParam12.setTypeId(11);
        productTypeParam12.setParamId(33);
        list.add(productTypeParam12);
        list.forEach(productParam -> {
            productParam.setAddOperator(1);
            productParam.setAddTime(new Date());
            productParam.setStatus(CommonDataStatus.OK.getStatus());
        });
        productTypeParamService.addList(list);
    }
}
