package shop4j.services.user;

import org.springframework.stereotype.Service;
import shop4j.models.user.User;
import shop4j.services.base.BaseService;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 14:38
 * @Description:用户业务
 */
@Service
public interface UserService extends BaseService<User>{

    /**
     * 根据用户账号获取用户
     * @param ticket 账号
     * @return 用户
     */
    User findByTicket(String ticket);

    /**
     * 通过客户ID冻结客户
     * @param id
     */
    void freezeUser(long id);
}
