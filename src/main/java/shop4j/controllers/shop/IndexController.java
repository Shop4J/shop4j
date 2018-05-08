package shop4j.controllers.shop;

import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop4j.annotions.shop.dataload.HeadDataLoad;
import shop4j.models.products.Product;
import shop4j.models.products.ProductImage;
import shop4j.models.products.ProductKid;
import shop4j.models.products.ProductType;
import shop4j.services.products.ProductImageService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductService;
import shop4j.services.products.ProductTypeService;

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
    @HeadDataLoad
    @GetMapping("/")
    public String index(Model model){

        List<ProductType> productTypes = productTypeService.findAll();

        Map<Long, List<ProductType>> productTypesMap = CollectionsParserUtil.collectFieldToMapList(productTypes,ProductType::getParentId);

        model.addAttribute("productTypesMap",productTypesMap);

        model.addAttribute("Long",new Long(0));

        List<ProductKid> productKidMax = productKidService.maxSellCountSuggest2Month();//热销推荐

        model.addAttribute("productKidMax",productKidMax);

        List<Long> spuIds = CollectionsParserUtil.collectFieldToList(productKidMax, ProductKid::getSpuId);

        List<Product> productMax = productService.findByIds(spuIds);

        model.addAttribute("productMaxMap",CollectionsParserUtil.collectFieldToMap(productMax,Product::getId));

        Map<Long, ProductImage> productImageMap = productImageService.findSPUMainImageByProductId(spuIds);

        model.addAttribute("productImageMap",productImageMap);
        return "/shop/index";
    }
}
