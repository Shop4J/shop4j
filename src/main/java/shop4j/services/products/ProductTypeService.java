package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductType;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:53
 * @Description:产品类型业务
 */
@Service
public interface ProductTypeService {
    /**
     * 获得全部得可用产品类型
     * @return
     */
    public List<ProductType> findAll();
}
