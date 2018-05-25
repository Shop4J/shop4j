package shop4j.services.base;

import org.springframework.stereotype.Service;
import shop4j.models.BaseModel;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:53
 * @Description:通用业务
 */
@Service
public interface BaseService<T extends BaseModel> {
    /**
     * 单个添加
     * @param t
     */
    void add(@NotNull T t);

    /**
     * 返回自增主键得添加方法
     * 并且属性原对象也有自增主键
     * @param t
     */
     long addReturnKey(@NotNull T t);

    /**
     * 批量添加
     * @param t
     */
    void addList(@NotNull List<T> t);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    T findById(@NotNull Long id);

    /**
     * 通过id集合查询
     * @param ids
     * @return
     */
    List<T> getByIds(@NotNull List<Long> ids);

    /**
     * 危险方法！适用于常量表,通过公用状态查询有效的
     * @return
     */
    List<T> findAll();

    int update(T t);

    /**
     * 通过实体查询，对于这个程序没用 全是基本类型 为null才会过滤
     * @param t
     * @return
     */
    List<T> select(T t);
}
