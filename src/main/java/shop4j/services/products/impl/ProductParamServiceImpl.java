package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductParamMapper;
import shop4j.models.products.ProductParam;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductParamService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 17:12
 * @Description:商品参数业务
 */
@Service
public class ProductParamServiceImpl extends BaseServiceImpl<ProductParam> implements ProductParamService{

}
