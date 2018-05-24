package shop4j.services.products.impl;

import base.util.collections.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop4j.dao.products.ProductMapper;
import shop4j.models.products.Product;
import shop4j.models.products.ProductImage;
import shop4j.models.products.ProductKid;
import shop4j.models.products.ProductParamValue;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductImageService;
import shop4j.services.products.ProductKidService;
import shop4j.services.products.ProductParamValueService;
import shop4j.services.products.ProductService;
import shop4j.vo.product.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: weixuedong
 * @Date: 2018/5/1 19:13
 * @Description:产品业务
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductKidService productKidService;

    @Autowired
    private ProductParamValueService productParamValueService;

    @Autowired
    private ProductImageService productImageService;
    @Override
    public PageInfo<Product> findBySearchVO(SearchProductVO searchProductVO) {
        PageHelper.startPage(searchProductVO.getPage(),searchProductVO.getSize());
        List<Product> products = productMapper.findBySearchVO(searchProductVO);
        PageInfo<Product> productsPageInfo = new PageInfo<>(products);
        return productsPageInfo;
    }

    @Override
    public  List<Product> findByTypesIndexSuggest(List<Long> typeIds) {
        List<Product> products = productMapper.findByTypes(typeIds,10);
        return products;
    }

    @Override
    public void removeTypeIndexSuggestCache() {

    }

    @Transactional
    @Override
    public void addProduct(ProductVO productVO,long operator) {
        if(!Objects.isNull(productVO)){

            Product product = new Product();
            product.setName(productVO.getName());
            product.setShowPrice(productVO.getShowPrice());
            product.setType(productVO.getProductType());
            product.setDetail(productVO.getDetail());
            product.setAddOperator(operator);
            long spuId = addReturnKey(product);

            List<ProductImage> productImages = new ArrayList<>();
            List<ProductParamValue> paramValues = new ArrayList<>();
            List<SKUVO> skuVOS = productVO.getSKUVOList();
            if(CollectionUtil.isNotEmpty(skuVOS)) {
                skuVOS.forEach(skuVO -> {
                    ProductKid productKid = new ProductKid();
                    productKid.setNum(skuVO.getNum());
                    productKid.setIsMain(skuVO.getIsMain());
                    productKid.setPrice(skuVO.getPrice());
                    productKid.setSpuId(spuId);
                    productKid.setAddOperator(operator);
                    long skuId = productKidService.addReturnKey(productKid);
                    List<ProductParamVO> params = skuVO.getProductParamBeanList();
                    if(CollectionUtil.isNotEmpty(params)) {
                        params.forEach(productParamVO -> {
                            ProductParamValue productParamValue = new ProductParamValue();
                            productParamValue.setParamId(productParamVO.getParamId());
                            productParamValue.setValue(productParamVO.getValue());
                            productParamValue.setProductId(skuId);
                            productParamValue.setAddOperator(operator);
                            paramValues.add(productParamValue);
                        });
                    }

                    List<ProductImageVO> imageBeans = skuVO.getProductImageBeans();
                    if(CollectionUtil.isNotEmpty(imageBeans)){
                        imageBeans.forEach(imageBean->{
                            ProductImage productImage = new ProductImage();
                            productImage.setAddOperator(operator);
                            productImage.setSort(imageBean.getSort());
                            productImage.setUrl(imageBean.getUrl());
                            productImage.setProductId(skuId);
                            productImage.setType(imageBean.getType());
                            productImages.add(productImage);
                        });
                    }
                });
                productParamValueService.addList(paramValues);
                productImageService.addList(productImages);
            }
        }
    }

}
