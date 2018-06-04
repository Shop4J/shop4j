package shop4j.services.login.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.user.LoginLogMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.user.LoginLog;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.login.LoginLogService;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/6/4 15:14
 * @Description:用户登陆日志
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements LoginLogService{
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public void log(String ip, String ticket, CommonDataStatus status) {
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(ip);
        loginLog.setStatus(status.getStatus());
        loginLog.setTicket(ticket);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public List<LoginLog> findTodayLogs(String ticket) {
        PageHelper.startPage(1,5);
        instanceCriteria().andEqualTo("ticket",ticket)
                .andGreaterThanOrEqualTo("addTime", LocalDate.now())
                .andLessThanOrEqualTo("addTime",LocalDate.now().plusDays(1));
        return loginLogMapper.selectByExample(exampleThreadLocal.get().orderBy("id desc"));
    }
}
