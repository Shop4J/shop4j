package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductKid;
import shop4j.models.products.ProductParam;
import shop4j.result.ProductParamResult;
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
    /**
     * 获得当前SKU属性和属性值 并获得其父类属性和属性值
     * 用于商品详情页
     * @param currentSKU 当前SKU
     * @return 商品属性封装
     */
    ProductParamResult findParams(ProductKid currentSKU);
}
