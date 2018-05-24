package shop4j.services.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop4j.dao.BaseMapper;
import shop4j.enums.CommonDataStatus;
import shop4j.models.BaseModel;
import shop4j.models.products.ProductKid;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:55
 * @Description:通用业务实现
 */
public class BaseServiceImpl<T extends BaseModel> implements BaseService<T >{
    @Autowired
    private  BaseMapper<T> baseMapper;

    protected ThreadLocal<Example> exampleThreadLocal = new ThreadLocal<>();
    @Override
    public void add(T t) {
        Date now = new Date();
        if(t.getStatus()==0){//默认有效
            t.setStatus(CommonDataStatus.OK.getStatus());
        }
        if(t.getAddOperator()==0){//默认认为是系统
            t.setAddOperator(1);
        }
        if(Objects.isNull(t.getAddTime())){
            t.setAddTime(now);
        }
        baseMapper.insert(t);
    }

    @Override
    public long addReturnKey(@NotNull T t) {
        Date now = new Date();
        if(t.getStatus()==0){//默认有效
            t.setStatus(CommonDataStatus.OK.getStatus());
        }
        if(t.getAddOperator()==0){//默认认为是系统
            t.setAddOperator(1);
        }
        if(Objects.isNull(t.getAddTime())){
            t.setAddTime(now);
        }
        baseMapper.insertUseGeneratedKeys(t);
        return t.getId();
    }

    @Override
    public void addList(List<T> t) {
        Date now = new Date();
        t.forEach(data ->{
            if(data.getStatus()==0){//默认有效
                data.setStatus(CommonDataStatus.OK.getStatus());
            }
            if(data.getAddOperator()==0){//默认认为是系统
                data.setAddOperator(1);
            }
            if(Objects.isNull(data.getAddTime())){
                data.setAddTime(now);
            }
        });
        baseMapper.insertList(t);
    }

    @Override
    public T findById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }
    protected  Example.Criteria instanceCriteria(){
        Class entityClass = (Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(entityClass);
        exampleThreadLocal.set(example);
        return example.createCriteria();
    }
    @Override
    public List<T> getByIds(List<Long> ids) {
        instanceCriteria().andIn("id",ids);
        return baseMapper.selectByExample(exampleThreadLocal.get());
    }

    @Override
    public List<T> findAll() {
        instanceCriteria().andEqualTo("status", CommonDataStatus.OK.getStatus());
        return baseMapper.selectByExample(exampleThreadLocal.get());
    }

    @Override
    public int update(T t) {
        return baseMapper.updateByPrimaryKey(t);
    }
}
