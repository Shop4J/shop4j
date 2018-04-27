package shop4j.controllers.shop;

import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop4j.annotions.shop.dataload.HeadDataLoad;
import shop4j.models.products.ProductType;
import shop4j.models.products.SearchMoney;
import shop4j.models.products.YearOld;
import shop4j.services.products.ColorService;
import shop4j.services.products.ProductTypeService;
import shop4j.services.products.SearchMoneyService;
import shop4j.services.products.YearOldService;

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
    /**
     * 商品搜索控制器
     */
    @GetMapping("/search")
    @HeadDataLoad
    public String searchIndex(Model model, String search){
        loadCommonData(model);//加载常量数据
        return "shop/products/product_list";
    }

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
}
