package shop4j.shceduling;

import base.util.collections.CollectionUtil;
import base.util.collections.parser.CollectionsParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shop4j.models.products.ProductType;
import shop4j.services.products.ProductService;
import shop4j.services.products.ProductTypeService;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/24 14:42
 * @Description:
 */
@Component
public class ProductCacheScheduled {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Scheduled(fixedDelay = 1000*60*30,initialDelay = 1000)
    public void indexSuggestCacheRemove(){
        log.debug("维护首页类型推荐缓存开始！");
        List<ProductType> productTypes = productTypeService.findAll();
        Map<Long, List<ProductType>> productTypesMap = CollectionsParserUtil.collectFieldToMapList(productTypes,ProductType::getParentId);
        productService.removeTypeIndexSuggestCache();
        List<ProductType> parentProductTypes = productTypesMap.get(Long.parseLong("0"));
        if(CollectionUtil.isNotEmpty(parentProductTypes)){
            productService.findByTypesIndexSuggest(CollectionsParserUtil.collectFieldToList(parentProductTypes,ProductType::getId));
            log.debug("维护首页类型推荐缓存开始！结束");
            return;
        }
        log.debug("无可维护得首页推荐！结束");
    }
}
