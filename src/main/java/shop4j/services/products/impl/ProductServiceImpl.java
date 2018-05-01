package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductMapper;
import shop4j.models.products.Product;
import shop4j.services.products.ProductService;
import shop4j.vo.SearchProductVO;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 19:13
 * @Description:产品业务
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findBySearchVO(SearchProductVO searchProductVO) {
        return null;
    }

    @Override
    public int add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public void addList(List<Product> products) {
        productMapper.insertList(products);
    }
}
