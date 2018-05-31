package shop4j.models.user;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 11:04
 * @Description:用户信息
 */
@Data
@Table(name = "db_user.tbl_user")
public class User extends BaseModel{
    /**
     * 账号
     */
    @Column
    private String ticket;
    /**
     * 密码
     */
    @Column
    private String password;
    /**
     * 手机号
     */
    @Column
    private String mobile;
    /**
     * 邮箱
     */
    @Column
    private String email;

    /**
     * 名字
     */
    @Column
    private String name;

}
