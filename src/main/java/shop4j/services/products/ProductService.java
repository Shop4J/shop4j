package shop4j.services.products;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import shop4j.models.products.Product;
import shop4j.services.base.BaseService;
import shop4j.vo.SearchProductVO;

import java.util.List;
import java.util.Map;

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
    List<Product> findByTypesIndexSuggest(List<Long> typeIds);

}
