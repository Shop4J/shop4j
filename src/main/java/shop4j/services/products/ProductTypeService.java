package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductType;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:53
 * @Description:产品类型业务
 */
@Service
public interface ProductTypeService extends BaseService<ProductType>{
    /**
     * 获得所有可用得子类型
     * @return 所有可用子类型
     */
    public List<ProductType> findAllKidTypes();
}
