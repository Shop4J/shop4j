package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.YearOld;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 11:26
 * @Description:适用年龄DB
 */
@Component
@Mapper
public interface YearOldMapper extends BaseMapper<YearOld>{

}
