package shop4j.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.user.UserRole;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 15:31
 * @Description:用户角色对应DB
 */
@Component
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole>{
}
