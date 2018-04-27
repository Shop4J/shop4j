package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.YearOldMapper;
import shop4j.models.products.YearOld;
import shop4j.services.products.YearOldService;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/27 11:32
 * @Description:年龄业务实现
 */
@Service
public class YearOldServiceImpl implements YearOldService{
    @Autowired
    private YearOldMapper yearOldMapper;
    @Override
    public List<YearOld> findAll() {
        return yearOldMapper.findAll();
    }
}
