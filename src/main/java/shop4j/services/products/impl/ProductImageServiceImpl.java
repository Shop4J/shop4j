package shop4j.services.products.impl;

import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ProductImageMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.enums.ProductImageTypeEnum;
import shop4j.models.products.ProductImage;
import shop4j.models.products.ProductKid;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ProductImageService;
import shop4j.services.products.ProductKidService;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:29
 * @Description:商品图片业务实现
 */
@Service
public class ProductImageServiceImpl extends BaseServiceImpl<ProductImage> implements ProductImageService{
    @Autowired
    private ProductKidService productKidService;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Override
    public Map<Long, ProductImage> findSPUMainImageBySpuIds(List<Long> spuIds) {

        List<ProductKid> skus = productKidService.findMainSkuListBySpuIds(spuIds);

        List<Long> skuIds = CollectionsParserUtil.collectFieldToList(skus, ProductKid::getId);

        Map<Long, ProductKid> skuMap = CollectionsParserUtil.collectFieldToMap(skus, ProductKid::getId);

        List<ProductImage> images = findSKUMainImageBySkuIds(skuIds);

        Map<Long,ProductImage> productImageMap = new HashMap<>();

        images.forEach(image->{

            ProductKid sku = skuMap.get(image.getProductId());

            productImageMap.put(sku.getSpuId(),image);

        });

        return productImageMap;
    }

    @Override
    public List<ProductImage> findImagesLists(long spuId,long skuId) {
        List<ProductImage> skuImages ;
        if(skuId == 0){//获得spu默认得主品图片
            ProductKid mainSku = productKidService.findMainSkuBySpuId(spuId);

            skuImages = findSKUImageBySkuId(mainSku.getId());
        }else{

            skuImages = findSKUImageBySkuId(skuId);
        }
        return skuImages;
    }

    @Override
    public List<ProductImage> findSKUMainImageBySkuIds(List<Long> skuIds) {
       instanceCriteria().andIn("productId",skuIds)
                .andEqualTo("type",ProductImageTypeEnum.SKU.getType())
                .andEqualTo("sort",1).andEqualTo("status", CommonDataStatus.OK.getStatus());
        List<ProductImage> productImage = productImageMapper.selectByExample(exampleThreadLocal.get());
        return productImage;
    }

    @Override
    public List<ProductImage> findSKUImageBySkuId(long skuId) {
        instanceCriteria().andEqualTo("productId",skuId)
                .andEqualTo("type",ProductImageTypeEnum.SKU.getType())
                .andEqualTo("status", CommonDataStatus.OK.getStatus());
        Example example = exampleThreadLocal.get();
        example.setOrderByClause("sort");
        List<ProductImage> productImage = productImageMapper.selectByExample(example);
        return productImage;
    }
}
