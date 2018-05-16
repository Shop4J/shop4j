package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductParam;
import shop4j.services.base.BaseService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:08
 * @Description:商品属性业务
 */
@Service
public interface ProductParamService extends BaseService<ProductParam>{

}
