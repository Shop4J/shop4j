package shop4j.services.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.BaseMapper;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:55
 * @Description:通用业务实现
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T>{
    @Autowired
    private BaseMapper baseMapper;
    @Override
    public void add(T t) {
        baseMapper.insert(baseMapper);
    }

    @Override
    public void addList(List<T> t) {
        baseMapper.insertList(t);
    }
}
