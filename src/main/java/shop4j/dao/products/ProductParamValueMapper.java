package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductParamValue;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/18 16:03
 * @Description:
 */
@Mapper
@Repository
public interface ProductParamValueMapper extends BaseMapper<ProductParamValue>{
    List<ProductParamValue> findDetailBySkuId(long skuId);
}
