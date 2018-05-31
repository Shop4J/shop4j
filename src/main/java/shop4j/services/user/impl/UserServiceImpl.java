package shop4j.services.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.models.user.User;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.user.UserService;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 14:46
 * @Description:用户业务实现
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    @Autowired
    private UserService userService;
    @Override
    public User findByTicket(String ticket) {
        return userService.findByTicket(ticket);
    }
}
