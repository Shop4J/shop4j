package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.YearOld;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 11:30
 * @Description:年龄范围业务
 */
@Service
public interface YearOldService extends BaseService<YearOld>{

    /**
     * 获得所有可用年龄
     * @return 可用年龄
     */
    List<YearOld> findAll();
}
