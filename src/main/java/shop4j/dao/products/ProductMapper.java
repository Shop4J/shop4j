package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.Product;
import shop4j.vo.product.SearchProductVO;

import java.util.Date;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 14:25
 * @Description:产品数据库操作
 */
@Mapper
@Component
public interface ProductMapper extends BaseMapper<Product>{
    /**
     * 自定义查询
     * @param searchProductVO 自定义参数
     * @return 商品
     */
    List<Product> findBySearchVO(SearchProductVO searchProductVO);

    List<Product> findByTypes(@Param("typeIds") List<Long> typeIds,@Param("size") int size);

}
