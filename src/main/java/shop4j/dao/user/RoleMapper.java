package shop4j.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.user.Role;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 15:32
 * @Description:角色DB
 */
@Mapper
@Component
public interface RoleMapper extends BaseMapper<Role>{
}
