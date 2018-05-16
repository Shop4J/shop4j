package shop4j.product;

import base.util.collections.parser.CollectionsParserUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.CommonDataStatus;
import shop4j.enums.ProductImageTypeEnum;
import shop4j.models.products.Product;
import shop4j.models.products.ProductImage;
import shop4j.models.products.ProductKid;
import shop4j.services.products.ProductImageService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductService;
import shop4j.vo.SearchProductVO;

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
    @Autowired
    private ProductKidService productKidService;
    @Autowired
    private ProductService productService;
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

    /**
     * SKU图片测试添加
     */
    @Test
    public void testProductKid(){
        SearchProductVO searchProductVO = new SearchProductVO();
        searchProductVO.setPage(1);
        searchProductVO.setSize(Integer.MAX_VALUE);
        PageInfo<Product> products = productService.findBySearchVO(searchProductVO);
        List<Long> spuIds = CollectionsParserUtil.collectFieldToList(products.getList(), Product::getId);
        List<ProductKid> skus = productKidService.getBySPUIds(spuIds);
        /**
         * 包太大得分批插入
         */
        skus.forEach(sku->{
            List<ProductImage> productImages = new ArrayList<>();
            for(int i=1;i<=10;i++) {
                ProductImage productImage = new ProductImage();
                productImage.setProductId(sku.getId());
                productImage.setSort(i);
                productImage.setType(ProductImageTypeEnum.SKU.getType());
                productImage.setAddTime(new Date());
                productImage.setUrl("/business/images/product/S-001-"+i+"_b.jpg");
                productImage.setStatus(CommonDataStatus.OK.getStatus());
                productImage.setAddOperator(1);
                productImages.add(productImage);
            }
            productImageService.addList(productImages);
        });

    }
}
