package shop4j.services.products;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import shop4j.models.products.ProductImage;
import shop4j.services.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:25
 * @Description:图片业务
 */
@Service
public interface ProductImageService extends BaseService<ProductImage>{
    /**
     * 获得SPU首图
     * @param spuIds SPU商品编号集合
     * @return 商品图片
     */
    public Map<Long,ProductImage> findSPUMainImageBySpuIds(List<Long> spuIds);

    /**
     * 通过spu获得SPU图片集合
     * @param spuId
     * @retur spu默认图
     */
    List<ProductImage> findImagesLists(long spuId,long skuId);

    /**
     * 搜索各SKU首图
     * @param skuIds sku编号集合
     * @return sku首图
     */
    public List<ProductImage> findSKUMainImageBySkuIds(List<Long> skuIds);

    /**
     * 通过sku获得sku得商品图片
     * @param skuId sku编号
     * @return 图片集合
     */
    public List<ProductImage> findSKUImageBySkuId(long skuId);
}
