package shop4j.services.products.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.products.ColorMapper;
import shop4j.models.products.Color;
import shop4j.models.products.ProductType;
import shop4j.services.base.BaseServiceImpl;
import shop4j.services.products.ColorService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/4/26 19:25
 * @Description:颜色业务实现
 */
@Service
public class ColorServiceImpl extends BaseServiceImpl<Color> implements ColorService {

    @Autowired
    private ColorMapper colorMapper;

    @Override
    public List<Color> findAll() {
        Example example = new Example(Color.class);
        example.createCriteria().andEqualTo("status",1);
        return colorMapper.selectByExample(example);
    }
}
