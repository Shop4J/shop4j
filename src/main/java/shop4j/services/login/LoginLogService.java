package shop4j.services.login;

import org.springframework.stereotype.Service;
import shop4j.enums.CommonDataStatus;
import shop4j.models.user.LoginLog;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/6/4 15:08
 * @Description:登陆日志业务
 */
@Service
public interface LoginLogService extends BaseService<LoginLog>{
    /**
     * 记录购物车登陆日志
     * @param ip
     * @param ticket 登陆的账号
     * @param status 1为成功登陆 2为失败登陆
     */
    void log(String ip, String ticket, CommonDataStatus status);

    /**
     * 获得今天指定ticket的登陆日志
     * @param ticket
     * @return
     */
    List<LoginLog> findTodayLogs(String ticket);
}
