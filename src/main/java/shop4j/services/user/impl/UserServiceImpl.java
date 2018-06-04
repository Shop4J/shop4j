package shop4j.services.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.user.UserMapper;
import shop4j.enums.CommonDataStatus;
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
    private UserMapper userMapper;
    @Override
    public User findByTicket(String ticket) {
        instanceCriteria().andEqualTo("ticket",ticket);
        return userMapper.selectOneByExample(exampleThreadLocal.get());
    }

    @Override
    public void freezeUser(long id) {
        userMapper.freezeByUserId(id);
    }
}
