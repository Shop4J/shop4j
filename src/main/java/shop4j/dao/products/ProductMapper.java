package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.Product;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 14:25
 * @Description:产品数据库操作
 */
@Mapper
@Component
public interface ProductMapper extends BaseMapper<Product>{

}
