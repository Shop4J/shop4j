package shop4j.services.products.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductMapper;
import shop4j.models.products.Product;
import shop4j.services.base.BaseService;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductService;
import shop4j.vo.SearchProductVO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 19:13
 * @Description:产品业务
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    @Autowired
    private ProductMapper productMapper;
    @Override
    public PageInfo<Product> findBySearchVO(SearchProductVO searchProductVO) {
        PageHelper.startPage(searchProductVO.getPage(),searchProductVO.getSize());
        List<Product> products = productMapper.findBySearchVO(searchProductVO);
        PageInfo<Product> productsPageInfo = new PageInfo<>(products);
        return productsPageInfo;
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id",ids);
        return productMapper.selectByExample(example);
    }

    @Override
    public  List<Product> findByTypesIndexSuggest(List<Long> typeIds) {
        List<Product> products = productMapper.findByTypes(typeIds, 10);
        return products;
    }

    @Override
    public Product findById(long id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
