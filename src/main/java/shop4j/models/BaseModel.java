package shop4j.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: weixuedong
 * @Date: 2018/4/22 21:31
 * @Description:通用数据库实体
 */
@Data
public class BaseModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    /**
     * 添加时间
     */
    @Column
    private Date addTime = new Date();

    /**
     * 更新时间
     */
    @Column
    private Date updateTime;

    /**
     * 添加人
     */
    @Column
    private long addOperator;

    /**
     * 更新人
     */
    @Column
    private long updateOperator;

    /**
     * @see shop4j.enums.CommonDataStatus
     */
    @Column
    private int status;
}
