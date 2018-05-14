package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductParam;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:06
 * @Description: 商品属性Dao
 */
@Mapper
@Component
public interface ProductParamMapper extends BaseMapper<ProductParam>{
}
