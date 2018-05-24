package shop4j.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.models.BaseModel;
import shop4j.models.products.ProductTypeParam;
import shop4j.services.base.BaseService;
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
        for(int type =11 ;type<=57;type++) {
            List<ProductTypeParam> list = new ArrayList<>();
            ProductTypeParam productTypeParam = new ProductTypeParam();
            productTypeParam.setTypeId(type);
            productTypeParam.setParamId(18);
            productTypeParam.setSkuSort(1);
            productTypeParam.setDetail(true);
            list.add(productTypeParam);

            ProductTypeParam productTypeParam1 = new ProductTypeParam();
            productTypeParam1.setTypeId(type);
            productTypeParam1.setParamId(19);
            productTypeParam1.setSkuSort(2);
            productTypeParam1.setDetail(true);
            productTypeParam1.setSpuSort(1);

            list.add(productTypeParam1);
            ProductTypeParam productTypeParam2 = new ProductTypeParam();
            productTypeParam2.setTypeId(type);
            productTypeParam2.setParamId(20);
            productTypeParam2.setSkuSort(3);
            productTypeParam2.setDetail(true);

            list.add(productTypeParam2);
            ProductTypeParam productTypeParam3 = new ProductTypeParam();
            productTypeParam3.setTypeId(type);
            productTypeParam3.setParamId(21);
            productTypeParam3.setSkuSort(4);
            productTypeParam3.setDetail(true);

            list.add(productTypeParam3);
            ProductTypeParam productTypeParam4 = new ProductTypeParam();
            productTypeParam4.setTypeId(type);
            productTypeParam4.setParamId(22);
            productTypeParam4.setSkuSort(5);
            productTypeParam4.setDetail(true);

            list.add(productTypeParam4);
            ProductTypeParam productTypeParam5 = new ProductTypeParam();
            productTypeParam5.setTypeId(type);
            productTypeParam5.setParamId(23);
            productTypeParam5.setSkuSort(6);
            productTypeParam5.setDetail(true);

            list.add(productTypeParam5);
            ProductTypeParam productTypeParam6 = new ProductTypeParam();
            productTypeParam6.setTypeId(type);
            productTypeParam6.setParamId(24);
            productTypeParam6.setSkuSort(7);
            productTypeParam6.setDetail(true);

            list.add(productTypeParam6);
            ProductTypeParam productTypeParam7 = new ProductTypeParam();
            productTypeParam7.setTypeId(type);
            productTypeParam7.setParamId(25);
            productTypeParam7.setSkuSort(8);
            productTypeParam7.setDetail(true);

            list.add(productTypeParam7);
            ProductTypeParam productTypeParam8 = new ProductTypeParam();
            productTypeParam8.setTypeId(type);
            productTypeParam8.setParamId(26);
            productTypeParam8.setSkuSort(9);
            productTypeParam8.setDetail(true);

            list.add(productTypeParam8);
            ProductTypeParam productTypeParam9 = new ProductTypeParam();
            productTypeParam9.setTypeId(type);
            productTypeParam9.setParamId(27);
            productTypeParam9.setSkuSort(10);
            productTypeParam9.setDetail(true);

            list.add(productTypeParam9);
            ProductTypeParam productTypeParam10 = new ProductTypeParam();
            productTypeParam10.setTypeId(type);
            productTypeParam10.setParamId(31);
            productTypeParam10.setSkuSort(11);
            productTypeParam10.setDetail(true);

            list.add(productTypeParam10);
            ProductTypeParam productTypeParam11 = new ProductTypeParam();
            productTypeParam11.setTypeId(type);
            productTypeParam11.setParamId(32);
            productTypeParam11.setSkuSort(12);
            productTypeParam11.setDetail(true);

            list.add(productTypeParam11);
            ProductTypeParam productTypeParam12 = new ProductTypeParam();
            productTypeParam12.setTypeId(type);
            productTypeParam12.setParamId(33);
            productTypeParam12.setSkuSort(13);
            productTypeParam12.setDetail(true);
            list.add(productTypeParam12);

            ProductTypeParam productTypeParam13 = new ProductTypeParam();
            productTypeParam13.setTypeId(type);
            productTypeParam13.setParamId(34);
            productTypeParam13.setSkuSort(14);
            productTypeParam13.setDetail(true);
            list.add(productTypeParam13);

            ProductTypeParam productTypeParam14 = new ProductTypeParam();
            productTypeParam14.setTypeId(type);
            productTypeParam14.setParamId(35);
            productTypeParam14.setSkuSort(15);
            productTypeParam14.setDetail(true);
            productTypeParam14.setSpuSort(2);
            list.add(productTypeParam14);

            list.forEach(productParam -> {
                productParam.setAddOperator(1);
                productParam.setAddTime(new Date());
                productParam.setStatus(CommonDataStatus.OK.getStatus());
            });
            productTypeParamService.addList(list);
        }
    }
}
