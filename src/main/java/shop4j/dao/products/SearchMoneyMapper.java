package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.SearchMoney;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 15:17
 * @Description:金钱搜索DB
 */
@Component
@Mapper
public interface SearchMoneyMapper extends BaseMapper<SearchMoney>{
    /**
     * 查询所有可用搜索
     * @return 可用金钱搜索
     */
    public List<SearchMoney> findAll();
}
