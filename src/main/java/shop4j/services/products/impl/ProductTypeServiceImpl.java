package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductTypeMapper;
import shop4j.models.products.ProductType;
import shop4j.services.products.ProductTypeService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:56
 * @Description:产品类型业务实现
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> findAll() {
        return productTypeMapper.findAll();
    }
}
