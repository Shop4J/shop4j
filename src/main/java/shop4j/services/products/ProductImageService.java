package shop4j.services.products;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import shop4j.models.products.ProductImage;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:25
 * @Description:图片业务
 */
@Service
public interface ProductImageService {
    /**
     * 获得SPU首图
     * @param productIds SPU商品编号集合
     * @return 商品图片
     */
    public Map<Long,ProductImage> findSPUMainImageByProductIds(List<Long> productIds);

    /**
     * 获得SPU首图
     * @param productId SPU商品编号
     * @return 商品图片
     */
    public ProductImage findSPUMainImageByProductId(long productId);

    /**
     * 批量添加
     * @param productImages
     */
    public void addList(List<ProductImage> productImages);

    /**
     * 通过spu获得SPU图片集合
     * @param spuId
     * @retur spu默认图
     */
    List<ProductImage> findImagesLists(long spuId);

    /**
     * 搜索各SKU首图
     * @param skuIds sku编号集合
     * @return sku首图
     */
    public List<ProductImage> findSKUMainImageBySkuIds(List<Long> skuIds);
}
