package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductType;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:51
 * @Description: 产品类型通用mapper
 */
@Mapper
@Component
public interface ProductTypeMapper extends BaseMapper<ProductType> {

}
