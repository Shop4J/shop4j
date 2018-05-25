package shop4j.product;

import base.util.collections.parser.CollectionsParserUtil;
import base.util.random.RandomUtil;
import base.util.time.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.models.products.Product;
import shop4j.models.products.ProductParamValue;
import shop4j.models.products.ProductType;
import shop4j.models.products.ProductTypeParam;
import shop4j.services.products.ProductParamValueService;
import shop4j.services.products.ProductService;
import shop4j.services.products.ProductTypeParamService;
import shop4j.services.products.ProductTypeService;
import shop4j.vo.product.ProductImageVO;
import shop4j.vo.product.ProductParamVO;
import shop4j.vo.product.ProductVO;
import shop4j.vo.product.SKUVO;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeParamService productTypeParamService;

    @Autowired
    private ProductParamValueService productParamValueService;
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
    @Test
    public void testAddOne(){
        Product product = new Product();
        product.setName("sdasdasd");
        product.setDetail("拉里萨说的啥的货物的核武器和动物i哦亲");
        product.setType(RandomUtil.rangeRandom(11,57));
        product.setAddTime(new Date());
        product.setAddOperator(1);
        product.setStatus(1);
        product.setShowPrice(BigDecimal.valueOf(new Random().nextInt(100)));
        System.err.println("商品主键："+productService.addReturnKey(product));
    }

    @Test
    public void testAdds(){
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("sdasdasd");
        product.setDetail("拉里萨说的啥的货物的核武器和动物i哦亲");
        product.setType(RandomUtil.rangeRandom(11,57));
        product.setAddTime(new Date());
        product.setAddOperator(1);
        product.setStatus(1);
        product.setShowPrice(BigDecimal.valueOf(new Random().nextInt(100)));
        productList.add(product);
        productService.addList(productList);
        System.err.println("商品主键："+productList.get(0).getId());
    }

    @Test
    public void testAddProduct(){
        ThreadPoolExecutor poolExecutor= new ThreadPoolExecutor(50,50,Long.MAX_VALUE, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        List<ProductType> allKidTypes = productTypeService.findAllKidTypes();
        final List<Long> kidTypeIds = CollectionsParserUtil.collectFieldToList(allKidTypes, ProductType::getId);
        int time=1;
        for(int i=1;i<=200000;i++) {
            poolExecutor.execute(()->{
                String detail = RandomUtil.rangeRandom(Arrays.asList("可好看了", "贼好看", "爆炸好看", "牛逼得好看", "无敌好看", "超级好看", "给力得衣服", "野果味道", "购车"));
                String name = RandomUtil.rangeRandom(
                        Arrays.asList(
                                "南极人新生儿礼盒婴儿衣服套装春夏纯棉初生儿男女宝宝满月礼品婴儿用品",
                                "南极人新生儿连体衣春秋季保暖婴儿衣服0-3个月男女宝宝春装爬服哈衣",
                                "南极人新生儿衣服春季婴儿连体衣长袖纯棉男女宝宝哈衣爬服",
                                "南极人婴儿衣服蝴蝶衣纯棉春装宝宝连体衣新生儿哈衣爬服0-3个月",
                                "hoo女童2018夏季新款纯棉连衣裙女孩无袖公主裙日系小清晰纱裙子",
                                "hoo男童夏季新款纯棉套头短袖t恤学生圆领上衣服个性帅气休闲童装",
                                "布布发现童装女童连衣裙夏季中国风印花盘扣百褶裙中大童裙子公主裙2018新",
                                "天诺音韵亲子装夏装三口四口全家装套装母子装母女装休闲运动2018新款沙滩户外郊游家庭装",
                                "俏宫举童装女童连衣裙2018夏季儿童公主裙女孩韩版刺绣中国风甜美裙子",
                                "俏宫举 童装男童春款儿童套装2018春季男孩子时尚连帽卫衣两件套",
                                "天诺音韵亲子装夏装2018新款一家三四口全家装休闲家庭装母子装母女装碎花图案短袖T恤套装"
                        )
                );
                ProductVO productVO = new ProductVO();
                productVO.setDetail(detail);
                productVO.setName(name);
                Long productType = RandomUtil.rangeRandom(kidTypeIds);
                productVO.setProductType(productType);
                productVO.setShowPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(7,400)));
                productVO.setSKUVOList(formatSKU(productType));
                productService.addProduct(productVO, 1);
            });
            System.err.println(time++);
        }
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private List<SKUVO> formatSKU(long type){
        List<SKUVO> skuvos = new ArrayList<>();
        SKUVO skuvo = new SKUVO();
        skuvo.setIsMain(1);
        skuvo.setNum(RandomUtil.rangeRandom(0,100));
        skuvo.setPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(7,400)));
        skuvo.setProductImageBeans(formateImage());
        skuvo.setProductParamBeanList(fomateParam(type));
        skuvos.add(skuvo);
        SKUVO skuvo2 = new SKUVO();
        skuvo2.setIsMain(0);
        skuvo2.setNum(RandomUtil.rangeRandom(0,100));
        skuvo2.setPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(7,400)));
        skuvo2.setProductImageBeans(formateImage());
        skuvo2.setProductParamBeanList(fomateParam(type));
        skuvos.add(skuvo2);
        SKUVO skuvo3 = new SKUVO();
        skuvo3.setIsMain(0);
        skuvo3.setNum(RandomUtil.rangeRandom(0,100));
        skuvo3.setPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(7,400)));
        skuvo3.setProductImageBeans(formateImage());
        skuvo3.setProductParamBeanList(fomateParam(type));
        skuvos.add(skuvo3);
        SKUVO skuvo4 = new SKUVO();
        skuvo4.setIsMain(0);
        skuvo4.setNum(RandomUtil.rangeRandom(0,100));
        skuvo4.setPrice(BigDecimal.valueOf(RandomUtil.rangeRandom(7,400)));
        skuvo4.setProductImageBeans(formateImage());
        skuvo4.setProductParamBeanList(fomateParam(type));
        skuvos.add(skuvo4);
        return skuvos;
    }
    private List<ProductParamVO> fomateParam(long type){
        List<ProductParamVO> productParamVOS=new ArrayList<>();
        List<ProductTypeParam> productTypeParams = productTypeParamService.findByType(type);
        productTypeParams.forEach(productTypeParam -> {
            ProductParamVO productParamVO = new ProductParamVO();
            productParamVO.setParamId(productTypeParam.getParamId());
            if(productTypeParam.getParamId()==18) {
                String param = RandomUtil.rangeRandom(Arrays.asList("亚麻", "丝绸", "合成纤维", "棉", "防雨绸", "布"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==19) {
                String param = RandomUtil.rangeRandom(Arrays.asList("蓝色", "黄色", "白色", "粉色", "绿色", "黑色"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==20) {
                String param = RandomUtil.rangeRandom(Arrays.asList("夏季","秋冬季","春秋季","冬季"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==21) {
                String param = RandomUtil.rangeRandom(Arrays.asList("亚麻", "丝绸", "合成纤维", "棉", "防雨绸", "布"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==22) {
                String param = RandomUtil.rangeRandom(Arrays.asList("适中", "薄款", "厚款", "加厚"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==23) {
                String param = RandomUtil.rangeRandom(Arrays.asList("A", "B", "C", "D"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==24) {
                String param = RandomUtil.rangeRandom(Arrays.asList("男", "女", "不限"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==25) {
                String param = RandomUtil.rangeRandom(Arrays.asList("套头", "拉链", "纽扣"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==26) {
                String param = RandomUtil.rangeRandom(Arrays.asList("无组合","上下装"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==27) {
                String param = RandomUtil.rangeRandom(Arrays.asList("1-6岁","6-12岁","12岁以内","12岁以上"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==31) {
                String param = RandomUtil.rangeRandom(Arrays.asList("校园风","公主风","简约","牛仔"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==32) {
                String param = RandomUtil.rangeRandom(Arrays.asList("纯色","卡通","风景"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==33) {
                String param = RandomUtil.rangeRandom(Arrays.asList("否","是","可拆"));
                productParamVO.setValue(param);
            }
            if(productTypeParam.getParamId()==35) {
                String param = RandomUtil.rangeRandom(Arrays.asList("XL","XXL","M","L"));
                productParamVO.setValue(param);
            }
            productParamVOS.add(productParamVO);
        });
        return productParamVOS;
    }
    private List<ProductImageVO> formateImage(){
        List<ProductImageVO> productImageVOS = new ArrayList<>();
        ProductImageVO productImageVO = new ProductImageVO();
        productImageVO.setSort(1);
        productImageVO.setType(2);
        productImageVO.setUrl("/business/images/product/M-"+RandomUtil.rangeRandom(0,19)+".jpg");
        productImageVOS.add(productImageVO);
        ProductImageVO productImageVO2 = new ProductImageVO();
        productImageVO2.setSort(2);
        productImageVO2.setType(2);
        productImageVO2.setUrl("/business/images/product/S-001-2_b.jpg");
        productImageVOS.add(productImageVO2);
        ProductImageVO productImageVO3 = new ProductImageVO();
        productImageVO3.setSort(3);
        productImageVO3.setType(2);
        productImageVO3.setUrl("/business/images/product/S-001-3_b.jpg");
        productImageVOS.add(productImageVO3);
        ProductImageVO productImageVO4 = new ProductImageVO();
        productImageVO4.setSort(4);
        productImageVO4.setType(2);
        productImageVO4.setUrl("/business/images/product/S-001-4_b.jpg");
        productImageVOS.add(productImageVO4);
        ProductImageVO productImageVO5 = new ProductImageVO();
        productImageVO5.setSort(5);
        productImageVO5.setType(2);
        productImageVO5.setUrl("/business/images/product/S-001-5_b.jpg");
        productImageVOS.add(productImageVO5);
        ProductImageVO productImageVO6 = new ProductImageVO();
        productImageVO6.setSort(6);
        productImageVO6.setType(2);
        productImageVO6.setUrl("/business/images/product/S-001-6_b.jpg");
        productImageVOS.add(productImageVO6);
        ProductImageVO productImageVO7 = new ProductImageVO();
        productImageVO7.setSort(7);
        productImageVO7.setType(2);
        productImageVO7.setUrl("/business/images/product/S-001-7_b.jpg");
        productImageVOS.add(productImageVO7);
        return productImageVOS;
    }

    @Test
    public void testSelect(){
        Product product = new Product();
        product.setAddOperator(1);
        List<Product> products = productService.select(product);
        System.err.println("products:"+products);
    }
}
