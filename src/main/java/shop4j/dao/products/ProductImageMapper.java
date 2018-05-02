package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductImage;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 16:21
 * @Description:商品图片DB
 */
@Mapper
@Component
public interface ProductImageMapper extends BaseMapper<ProductImage>{
    /**
     *  通过产品编号和图片类型 获得产品首图
     * @param productIds 图片编号
     * @param type 图片类型 ProductImageTypeEnum中的一个
     * @return 图片信息
     */
    public List<ProductImage> findFirstByProductIdsAndType(@Param("productIds")List<Long> productIds,@Param("type") int type);
}
