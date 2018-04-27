package shop4j.dao;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: xuhao
 *  mybatis的通用mapper
 * @Date：Created on 2018/3/7 18:29.
 */
@Component
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
