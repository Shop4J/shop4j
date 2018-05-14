package shop4j.services.base;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: weixuedong
 * @Date: 2018/5/14 18:53
 * @Description:通用业务
 */
@Service
public interface BaseService<T> {
    /**
     * 单个添加
     * @param t
     */
    void add(T t);

    /**
     * 批量添加
     * @param t
     */
    void addList(List<T> t);
}
