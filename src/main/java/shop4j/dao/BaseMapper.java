package shop4j.dao;

import org.springframework.stereotype.Repository;
import shop4j.models.BaseModel;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper
 * @param <T>
 */
@Repository
public interface BaseMapper<T extends BaseModel> extends Mapper<T>, MySqlMapper<T> ,IdsMapper<T>{

}
