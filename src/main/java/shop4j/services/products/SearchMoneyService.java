package shop4j.services.products;

import org.springframework.stereotype.Service;
import shop4j.models.products.SearchMoney;
import shop4j.services.base.BaseService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 15:20
 * @Description: 金钱搜索业务
 */
@Service
public interface SearchMoneyService extends BaseService<SearchMoney>{
    /**
     * 搜索所有可用金钱范围
     * @return 可用金钱范围
     */
    public List<SearchMoney> findAll();
}
