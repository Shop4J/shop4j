package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductTypeParamMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.products.ProductTypeParam;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductTypeParamService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:52
 * @Description:
 */
@Service
public class ProductTypeParamServiceImpl extends BaseServiceImpl<ProductTypeParam>  implements ProductTypeParamService {

    @Autowired
    private ProductTypeParamMapper productTypeParamMapper;

    @Override
    public List<ProductTypeParam> findByType(long type) {
        instanceCriteria().andEqualTo("typeId",type).andEqualTo("status", CommonDataStatus.OK.getStatus());
        return productTypeParamMapper.selectByExample(exampleThreadLocal.get());
    }

    @Override
    public List<ProductTypeParam> findByTypeAndParamIds(long type, List<Long> paramIds) {
        instanceCriteria().andEqualTo("typeId",type).andIn("paramId",paramIds);
        return productTypeParamMapper.selectByExample(exampleThreadLocal.get());
    }
}
