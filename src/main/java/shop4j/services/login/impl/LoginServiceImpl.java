package shop4j.services.login.impl;

import base.util.collections.CollectionUtil;
import base.util.collections.parser.CollectionsParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop4j.enums.LoginStatusEnum;
import shop4j.models.user.Role;
import shop4j.models.user.User;
import shop4j.models.user.UserRole;
import shop4j.result.LoginResult;
import shop4j.services.login.LoginService;
import shop4j.services.user.RoleService;
import shop4j.services.user.UserRoleService;
import shop4j.services.user.UserService;
import shop4j.vo.login.LoginVO;
import shop4j.vo.login.UserDetailVO;

import java.util.List;
import java.util.Objects;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 14:37
 * @Description: 登陆业务实现
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;
    @Override
    public LoginResult login(LoginVO loginVO) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByTicket(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(LoginStatusEnum.Error.getMsg());
        }
        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setStatus(user.getStatus());
        userDetailVO.setUsername(user.getTicket());
        userDetailVO.setPassword(user.getPassword());
        List<UserRole> userRoles = userRoleService.findByUserId(user.getId());
        if(CollectionUtil.isNotEmpty(userRoles)) {
            List<Long> roleIds = CollectionsParserUtil.collectFieldToList(userRoles, UserRole::getRoleId);
            List<Role> roles = roleService.getByIds(roleIds);
            userDetailVO.setRoles(roles);
        }
        return userDetailVO;
    }
}
