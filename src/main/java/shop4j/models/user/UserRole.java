package shop4j.models.user;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 11:54
 * @Description:用户权限对应表
 */
@Table(name ="db_user.tbl_user_role")
@Data
public class UserRole extends BaseModel{
    @Column
    private long userId;

    @Column
    private long roleId;
}
