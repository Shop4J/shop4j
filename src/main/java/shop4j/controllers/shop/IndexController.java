package shop4j.controllers.shop;

import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop4j.annotions.shop.dataload.HeadDataLoad;
import shop4j.models.products.Product;
import shop4j.models.products.ProductImage;
import shop4j.models.products.ProductType;
import shop4j.services.order.OrderDetailService;
import shop4j.services.products.ProductImageService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductService;
import shop4j.services.products.ProductTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 11:00
 * @Description:商城首页
 */
@Controller
public class IndexController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductKidService productKidService;
    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private OrderDetailService orderDetailService;
    @HeadDataLoad
    @GetMapping("/")
    public String index(Model model){
        List<ProductType> productTypes = productTypeService.findAll();

        Map<Long, List<ProductType>> productTypesMap = CollectionsParserUtil.collectFieldToMapList(productTypes,ProductType::getParentId);

        model.addAttribute("productTypesMap",productTypesMap);

        List<Long> spuIds = new ArrayList<>();

        List<Long> maxSellSpuIds = orderDetailService.findMaxSellCount(8);

        model.addAttribute("maxSellSpuIds",maxSellSpuIds);

        spuIds.addAll(maxSellSpuIds);

        List<Product> productMax = productService.getByIds(maxSellSpuIds);

        model.addAttribute("productMaxMap",CollectionsParserUtil.collectFieldToMap(productMax,Product::getId));

        List<ProductType> parentTypes = productTypesMap.get((long)0);

        List<Product> products = productService.findByTypesIndexSuggest(CollectionsParserUtil.collectFieldToList(parentTypes, ProductType::getId));//首页类型推荐

        spuIds.addAll(CollectionsParserUtil.collectFieldToList(products, Product::getId));

        Map<Long, ProductImage> productImageMap = productImageService.findSPUMainImageBySpuIds(spuIds);

        model.addAttribute("productImageMap",productImageMap);

        Map<Long, Integer> sellCountMap = orderDetailService.sellCountBySPUIds(spuIds);

        model.addAttribute("sellCountMap",sellCountMap);//销量

        Map<Long, List<Product>> typeProductMap = CollectionsParserUtil.collectFieldToMapList(products, Product::getType);

        model.addAttribute("typeProductMap",typeProductMap);//按产品父类型区分产品
        return "/shop/index";
    }
}
