package shop4j.models.sets;

import lombok.Data;
import shop4j.models.BaseModel;

import javax.persistence.*;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 15:43
 * @Description:站点信息
 */
@Table(name = "db_web_set.tbl_web_info")
@Data
public class WebInfo extends BaseModel {

    /**
     * 站点名称
     */
    @Column
    private String name;

    /**
     * 站点类型
     * @see shop4j.enums.WebInfoTypeEnum
     */
    @Column
    private int type;

    /**
     * logo地址，因为logo要替换所以不能写死
     */
    @Column(name = "logo_url")
    private String logoUrl;

    /**
     * 关键词
     */
    @Column(name = "key_world")
    private String keyWorld;
}
