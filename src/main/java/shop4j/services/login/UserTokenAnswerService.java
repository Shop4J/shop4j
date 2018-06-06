package shop4j.services.login;

import org.springframework.stereotype.Service;
import shop4j.models.user.UserTokenAnswer;
import shop4j.services.base.BaseService;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:32
 * @Description:用户验证码答案业务
 */
@Service
public interface UserTokenAnswerService extends BaseService<UserTokenAnswer>{
}
