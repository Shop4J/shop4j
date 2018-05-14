package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductTypeParam;
import shop4j.services.base.BaseService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:48
 * @Description: 商品类型与商品属性映射业务
 */
@Service
public interface ProductTypeParamService extends BaseService<ProductTypeParam>{

}
