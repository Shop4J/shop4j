package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductParamMapper;
import shop4j.models.products.ProductParam;
import shop4j.services.products.ProductParamService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:12
 * @Description:商品参数业务
 */
@Service
public class ProductParamServiceImpl implements ProductParamService{
    @Autowired
    private ProductParamMapper productParamMapper;
    @Override
    public void addParam(@NotNull ProductParam param) {
        productParamMapper.insert(param);
    }

    @Override
    public void addParams(@NotNull List<ProductParam> params) {
        productParamMapper.insertList(params);
    }
}
