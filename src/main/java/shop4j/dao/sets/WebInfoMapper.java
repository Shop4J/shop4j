package shop4j.dao.sets;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.sets.WebInfo;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 15:39
 * @Description:站点信息D
 */
@Component
@Mapper
public interface WebInfoMapper extends BaseMapper<WebInfo> {

    /**
     * 通过站点信息类型获得站点信息
     * @param type webInfo里得Type
     * @return 站点信息
     */
    WebInfo findByType(int type);
}
