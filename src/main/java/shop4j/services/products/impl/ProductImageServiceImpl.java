package shop4j.services.products.impl;

import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductImageMapper;
import shop4j.enums.ProductImageTypeEnum;
import shop4j.models.products.ProductImage;
import shop4j.services.products.ProductImageService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:29
 * @Description:商品图片业务实现
 */
@Service
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    private ProductImageMapper productImageMapper;
    @Override
    public Map<Long, ProductImage> findSPUMainImageByProductId(List<Long> productIds) {
        Example example = new Example(ProductImage.class);
        example.createCriteria().andIn("productId",productIds).andEqualTo("type",ProductImageTypeEnum.SPU.getType());
        List<ProductImage> productImages = productImageMapper.selectByExample(example);
        return CollectionsParserUtil.collectFieldToMap(productImages,ProductImage::getProductId);

    }

    @Override
    public void addList(List<ProductImage> productImages) {

        productImageMapper.insertList(productImages);

    }
}
