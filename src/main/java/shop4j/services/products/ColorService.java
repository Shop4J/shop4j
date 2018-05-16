package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.Color;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/26 19:25
 * @Description:颜色业务
 */
@Service
public interface ColorService extends BaseService<Color>{
    /**
     * 获得可用颜色
     * @return 可用颜色
     */
    public List<Color> findAll();
}
