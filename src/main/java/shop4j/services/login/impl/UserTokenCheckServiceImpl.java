package shop4j.services.login.impl;

import org.springframework.stereotype.Service;
import shop4j.models.user.UserTokenCheck;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.login.UserTokenCheckService;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:46
 * @Description:用户验证码检测业务实现
 */
@Service
public class UserTokenCheckServiceImpl extends BaseServiceImpl<UserTokenCheck> implements UserTokenCheckService{
}
