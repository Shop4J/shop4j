package shop4j.services.products;

import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import shop4j.models.products.Product;
import shop4j.services.base.BaseService;
import shop4j.vo.product.ProductVO;
import shop4j.vo.product.SearchProductVO;

import java.util.Date;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 19:12
 * @Description:产品业务
 */
@Service
public interface ProductService extends BaseService<Product>{

    /**
     * 通过搜索页面参数
     * 查询可用商品
     * @return 可用商品SPU
     */
    PageInfo<Product> findBySearchVO(SearchProductVO searchProductVO);

    /**
     * 通过产品类型获得首页推荐
     * @param typeIds
     * @return
     */
    @Cacheable(value = "typeSuggest",key = "'typeIndexCache'",sync = true)
    List<Product> findByTypesIndexSuggest(List<Long> typeIds);

    @CacheEvict(value="typeSuggest",key="'typeIndexCache'")
    public void removeTypeIndexSuggestCache();

    /**
     * 逻辑添加商品
     * @param productVO 单个
     */
    void addProduct(ProductVO productVO,long operator);


}
