package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductTypeParam;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:48
 * @Description: 商品类型与商品属性映射业务
 */
@Service
public interface ProductTypeParamService extends BaseService<ProductTypeParam>{
    /**
     * 通过商品类型活动商品参数
     * @param type 商品类型
     * @return 商品类型对应的参数
     */
    List<ProductTypeParam> findByType(long type);

    /**
     * 通过类型+参数ID获取商品类型参数
     * @param type 类型编号
     * @param paramIds 参数ID
     * @return 商品类型对应参数
     */
    List<ProductTypeParam> findByTypeAndParamIds(long type,List<Long> paramIds);
}
