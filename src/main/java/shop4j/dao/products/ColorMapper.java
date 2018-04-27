package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.Color;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/26 19:22
 * @Description:
 */
@Component
@Mapper
public interface ColorMapper extends BaseMapper<Color> {
    /**
     * 查询全部可用颜色
     * @return 可用颜色
     */
    public List<Color> findAll();
}
