package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductTypeParam;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:45
 * @Description: 产品类型类型参数映射dao
 */
@Component
@Mapper
public interface ProductTypeParamMapper extends BaseMapper<ProductTypeParam>{
}
