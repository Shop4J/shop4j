package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.ProductKid;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: weixuedong
 * @Date: 2018/5/7 10:58
 * @Description:SKU数据库业务
 */
@Mapper
@Component
public interface ProductKidMapper extends BaseMapper<ProductKid>{

    /**
     * 通过SPUID计算库存
     * @param spuIds
     * @return key是spuid value是数量
     */
    List<ProductKid> countStoreBySPU(@Param("spuIds") Set<Long> spuIds);

    /**
     * 获得一定范围内销量最高得商品
     * @param dateBegin
     * @return
     */
    List<ProductKid> maxSellCount(@Param("dateBegin") LocalDate dateBegin);
}
