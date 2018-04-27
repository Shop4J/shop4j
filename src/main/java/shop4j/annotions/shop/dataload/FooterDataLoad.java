package shop4j.annotions.shop.dataload;

import java.lang.annotation.*;

/**
 * @Author: weixuedong
 * @Date: 2018/4/21 17:03
 * @Description:尾部数据加载
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FooterDataLoad {
}
