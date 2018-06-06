package shop4j.models.user;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: weixuedong
 * @Date: 2018/6/6 16:18
 * @Description: 用户名-验证码答案
 */
@Data
@Table(name = "tbl_user_token_answer")
public class UserTokenAnswer extends BaseModel{
    @Id
    private long id;

    /**
     * @see UserTokenCheck
     */
    @Column
    private long tokenCheckId;

    /**
     * 横坐标
     */
    @Column
    private double x;

    /**
     * 纵坐标
     */
    @Column
    private double y;

    /**
     * 排序
     */
    @Column
    private int sort;
}
