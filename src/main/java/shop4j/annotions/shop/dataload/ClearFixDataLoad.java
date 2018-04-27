package shop4j.annotions.shop.dataload;

import java.lang.annotation.*;

/**
 * @Author: weixuedong
 * @Date: 2018/4/21 17:50
 * @Description: 独立得底部信息，只有备案信息和企业超链接模块
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearFixDataLoad {
}
