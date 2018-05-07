package shop4j.controllers.shop;

import base.util.collections.CollectionUtil;
import base.util.collections.parser.CollectionsParserUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop4j.models.order.OrderDetail;
import shop4j.models.products.*;
import shop4j.services.order.OrderDetailService;
import shop4j.services.products.*;
import shop4j.vo.SearchProductVO;
import shop4j.annotions.shop.dataload.HeadDataLoad;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/4/26 15:57
 * @Description:商品控制器
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ColorService colorService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private YearOldService yearOldService;

    @Autowired
    private SearchMoneyService searchMoneyService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductKidService productKidService;
    /**
     * 商品搜索首页控制器
     * @param model thymeleaf模板
     * @param searchText 搜索文本
     * @param productKidType 子产品类型
     * @return 搜索商品首页
     */
    @GetMapping("/search")
    @HeadDataLoad
    public String searchIndex(Model model, String searchText,Long productKidType ){
        loadCommonData(model);//加载常量数据
        model.addAttribute("searchText",searchText);
        model.addAttribute("productKidType",productKidType);
        return "shop/products/product_search_index";
    }

    /**
     * 加载各种常量
     * @param model
     */
    private void loadCommonData(Model model){
        model.addAttribute("colors",colorService.findAll());

        List<ProductType> productTypes = productTypeService.findAll();

        Map<Long, List<ProductType>> productTypesMap = CollectionsParserUtil.collectFieldToMapList(productTypes,ProductType::getParentId);

        model.addAttribute("productTypesMap",productTypesMap);

        List<YearOld> yearOlds = yearOldService.findAll();

        model.addAttribute("yearOlds",yearOlds);

        List<SearchMoney> searchMoneys = searchMoneyService.findAll();

        model.addAttribute("searchMoneys",searchMoneys);
    }

    /**
     * 刷新商品
     */
    @GetMapping("/list")
    public String searchProduct(Model model,@NotNull SearchProductVO searchProductVO){
        if(searchProductVO.getPage()==0){
            searchProductVO.setPage(1);
        }
        if(searchProductVO.getSize()!=20){
            searchProductVO.setSize(20);
        }
        PageInfo<Product> productPageInfo = productService.findBySearchVO(searchProductVO);
        model.addAttribute("productPageInfo",productPageInfo);

        List<Product> products = productPageInfo.getList();

        if(CollectionUtil.isNotEmpty(products)) {
            List<Long> productIds = CollectionsParserUtil.collectFieldToList(products, Product::getId);
            Map<Long, ProductImage> productImageMap = productImageService.findSPUMainImageByProductId(productIds);
            model.addAttribute("productImageMap", productImageMap);//商品图片

            Map<Long, Integer> sellCountMap = orderDetailService.sellCountBySPUIds(productIds);//销量
            model.addAttribute("sellCountMap", sellCountMap);

            Map<Long, Integer> storeCountMap = productKidService.countStoreBySpuIds(productIds);//库存
            model.addAttribute("storeCountMap",storeCountMap);
        }
        return "shop/products/product_list :: productList";
    }

    /**
     * 刷新商品
     */
    @GetMapping("/detail")
    @HeadDataLoad
    public String searchProduct(Model model,long spuId){

       return "shop/products/item_show";
    }
}
