package shop4j.annotions.shop.dataload;

import java.lang.annotation.*;

/**
 * @Author: weixuedong
 * @Date: 2018/4/21 16:57
 * @Description: 用于加载顶部信息
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TopDataLoad {
}
