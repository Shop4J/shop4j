package shop4j.services.sets;

import org.springframework.stereotype.Service;
import shop4j.models.sets.WebInfo;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 15:21
 * @Description:项目信息服务接口
 */
@Service
public interface WebInfoService {

    /**
     * 新增站点信息
     * @param webInfo
     * @return
     */
    int add(WebInfo webInfo);

    /**
     * 获得主站站点信息
     * @return 站点信息
     */
    WebInfo getWebRoot();
}
