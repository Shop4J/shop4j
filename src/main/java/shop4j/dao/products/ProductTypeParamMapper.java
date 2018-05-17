package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductTypeParam;

/**
 * @Author: weixuedong
 * @Date: 2018/5/15 15:40
 * @Description:
 */
@Mapper
@Repository
public interface ProductTypeParamMapper extends BaseMapper<ProductTypeParam>{
}
