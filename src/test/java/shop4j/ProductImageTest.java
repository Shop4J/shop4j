package shop4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.enums.ProductImageTypeEnum;
import shop4j.models.products.Product;
import shop4j.models.products.ProductImage;
import shop4j.services.products.ProductImageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:51
 * @Description:商品图片测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductImageTest {
    @Autowired
    private ProductImageService productImageService;
    @Test
    public void test(){
        List<ProductImage> productImages = new ArrayList<>();
        for(int i=1 ;i<=5000;i++) {
            ProductImage productImage = new ProductImage();
            productImage.setProductId(80006+i);
            productImage.setSort(1);
            productImage.setType(ProductImageTypeEnum.SPU.getType());
            productImage.setAddTime(new Date());
            productImage.setUrl("/business/images/product/M-"+(i-1)%20+".jpg");
            productImage.setStatus(CommonDataStatus.OK.getStatus());
            productImage.setAddOperator(1);
            productImages.add(productImage);
        }
        productImageService.addList(productImages);
    }
}
