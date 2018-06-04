package shop4j.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.user.LoginLog;

/**
 * @Author: weixuedong
 * @Date: 2018/6/4 15:32
 * @Description:登陆日志db
 */
@Mapper
@Component
public interface LoginLogMapper extends BaseMapper<LoginLog>{

}
