package shop4j.models.user;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/6/4 15:18
 * @Description:用户登陆日志
 */
@Data
@Table(name ="db_user.tbl_login_log")
public class LoginLog extends BaseModel{
    @Column
    private String ip;
    /**
     * 1是成功登陆 2是失败登陆
     */
    @Column
    private int status;

    /**
     * 登陆的账号信息
     */
    @Column
    private String ticket;
}
