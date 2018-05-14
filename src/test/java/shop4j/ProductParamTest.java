package shop4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.models.products.ProductParam;
import shop4j.services.products.ProductParamService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:29
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductParamTest {
    @Autowired
    private ProductParamService productParamService;
    @Test
    public void testAdd(){
        List<ProductParam> params = new ArrayList<>();
        ProductParam productParam1 = new ProductParam();
        productParam1.setName("outlining");
        productParam1.setMeaing("面料");
        params.add(productParam1);
        ProductParam productParam2 = new ProductParam();
        productParam2.setName("color");
        productParam2.setMeaing("颜色");
        params.add(productParam2);
        ProductParam productParam3 = new ProductParam();
        productParam3.setName("season");
        productParam3.setMeaing("季节");
        params.add(productParam3);
        ProductParam productParam4 = new ProductParam();
        productParam4.setName("InLining");
        productParam4.setMeaing("里料");
        params.add(productParam4);
        ProductParam productParam5 = new ProductParam();
        productParam5.setName("size");
        productParam5.setMeaing("尺码");
        ProductParam productParam6 = new ProductParam();
        productParam6.setName("thickness");
        productParam6.setMeaing("厚度");
        params.add(productParam6);
        ProductParam productParam7 = new ProductParam();
        productParam7.setName("safeLevel");
        productParam7.setMeaing("安全等级");
        params.add(productParam7);
        ProductParam productParam9 = new ProductParam();
        productParam9.setName("sex");
        productParam9.setMeaing("适用性别");
        params.add(productParam9);
        ProductParam productParam10 = new ProductParam();
        productParam10.setName("clothingDoorFlap");
        productParam10.setMeaing("衣门襟");
        params.add(productParam10);
        ProductParam productParam11 = new ProductParam();
        productParam11.setName("group");
        productParam11.setMeaing("组合形式");
        params.add(productParam11);
        ProductParam productParam12 = new ProductParam();
        productParam12.setName("age");
        productParam12.setMeaing("适用年龄");
        params.add(productParam12);
        ProductParam productParam13 = new ProductParam();
        productParam13.setName("trousersDoorFlap");
        productParam13.setMeaing("裤门襟");
        params.add(productParam13);
        ProductParam productParam14 = new ProductParam();
        productParam14.setName("trousersWaist");
        productParam14.setMeaing("裤腰");
        params.add(productParam14);
        ProductParam productParam15= new ProductParam();
        productParam15.setName("trousersLength");
        productParam15.setMeaing("裤长");
        params.add(productParam15);
        ProductParam productParam16= new ProductParam();
        productParam16.setName("style");
        productParam16.setMeaing("风格");
        params.add(productParam16);
        ProductParam productParam17= new ProductParam();
        productParam17.setName("type");
        productParam17.setMeaing("分类");
        params.add(productParam17);
        ProductParam productParam18= new ProductParam();
        productParam18.setName("isTakeHat");
        productParam18.setMeaing("是否带帽");
        params.add(productParam18);
        params.forEach(productParam -> {
            productParam.setAddOperator(1);
            productParam.setAddTime(new Date());
            productParam.setStatus(CommonDataStatus.OK.getStatus());
        });
        productParamService.addParams(params);
    }

}
