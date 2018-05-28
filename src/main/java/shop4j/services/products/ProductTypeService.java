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
    List<ProductType> findAllKidTypes();

    /**
     * 通过父类型获得所有子类型
     * @param parentIds 父类型
     * @return 所有子类型
     */
    List<ProductType> findByParentIds(List<Long> parentIds);
}
