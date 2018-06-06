package shop4j.services.login.impl;

import org.springframework.stereotype.Service;
import shop4j.models.user.UserTokenAnswer;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.login.UserTokenAnswerService;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:35
 * @Description:用户验证码答案
 */
@Service
public class UserTokenAnswerServiceImpl extends BaseServiceImpl<UserTokenAnswer> implements UserTokenAnswerService{
}
