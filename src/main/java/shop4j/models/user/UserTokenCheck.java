package shop4j.models.user;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 11:50
 * @Description:用户名登陆是否需要检测验证码
 */
@Data
@Table(name = "tbl_user_token_check")
public class UserTokenCheck extends BaseModel{
    @Column
    private String username;
}
