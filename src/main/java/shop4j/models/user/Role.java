package shop4j.models.user;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 11:40
 * @Description:系统权限
 */
@Table(name ="db_user.tbl_role")
@Data
public class Role extends BaseModel{
    /**
     * 权限名
     */
    @Column
    private String name;
}
