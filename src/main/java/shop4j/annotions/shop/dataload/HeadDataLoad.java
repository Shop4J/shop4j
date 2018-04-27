package shop4j.annotions.shop.dataload;

import java.lang.annotation.*;

/**
 * @Author: weixuedong
 * @Date: 2018/4/21 16:53
 * @Description:用于切面加载头信息
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HeadDataLoad {
}
