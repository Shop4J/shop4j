package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.SearchMoneyMapper;
import shop4j.models.products.SearchMoney;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.SearchMoneyService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 15:33
 * @Description: 金钱搜索业务实现
 */
@Service
public class SearchMoneyServiceImpl extends BaseServiceImpl<SearchMoney> implements SearchMoneyService{

}
