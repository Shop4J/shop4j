package shop4j.dao.products;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import shop4j.dao.BaseMapper;
import shop4j.models.products.SearchMoney;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 15:17
 * @Description:金钱搜索DB
 */
@Component
@Mapper
public interface SearchMoneyMapper extends BaseMapper<SearchMoney>{

}
