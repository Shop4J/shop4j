package shop4j.services.user;

import org.springframework.stereotype.Service;
import shop4j.models.user.UserRole;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 15:14
 * @Description:
 */
@Service
public interface UserRoleService {
    /**
     * 通过用户ID获取用户对应角色
     * @param userId
     * @return 用户角色对应
     */
    List<UserRole> findByUserId(long userId);
}
