package shop4j.services.login;

import org.springframework.stereotype.Service;
import shop4j.models.user.UserTokenCheck;
import shop4j.services.base.BaseService;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:45
 * @Description:用户验证码检测业务
 */
@Service
public interface UserTokenCheckService extends BaseService<UserTokenCheck>{
}
