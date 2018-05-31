package shop4j.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.user.User;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 14:49
 * @Description:用户表db
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User>{

}
