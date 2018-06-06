package shop4j.services.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.user.UserRoleMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.user.UserRole;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.login.UserRoleService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 15:19
 * @Description:用户角色对应业务
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService{
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> findByUserId(long userId) {
        instanceCriteria().andEqualTo("userId",userId).andEqualTo("status", CommonDataStatus.OK.getStatus());
        return userRoleMapper.selectByExample(exampleThreadLocal.get());
    }
}
