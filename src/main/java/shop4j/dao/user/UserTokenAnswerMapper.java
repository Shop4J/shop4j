package shop4j.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import shop4j.dao.BaseMapper;
import shop4j.models.user.UserTokenAnswer;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:27
 * @Description:用户验证码答案db
 */
@Mapper
@Repository
public interface UserTokenAnswerMapper extends BaseMapper<UserTokenAnswer>{
}
