package shop4j.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import shop4j.dao.BaseMapper;
import shop4j.models.user.UserTokenCheck;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:38
 * @Description:用户名检测DB
 */
@Mapper
@Repository
public interface UserTokenCheckMapper extends BaseMapper<UserTokenCheck>{
}
