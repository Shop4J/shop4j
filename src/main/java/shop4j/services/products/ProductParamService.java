package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.ProductParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:08
 * @Description:商品属性业务
 */
@Service
public interface ProductParamService {
    /**
     * 单个添加
     * @param param 商品参数
     */
    public void addParam(@NotNull ProductParam param);

    /**
     * 批量添加
     * @param params 商品参数集合
     */
    public void addParams(@NotNull List<ProductParam> params);
}
