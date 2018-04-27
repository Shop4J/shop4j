package shop4j.aspects.shop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Author: weixuedong
 * @Date: 2018/4/24 19:21
 * @Description:公共工具类加载
 */
@Aspect
@Order(5)
@Component
public class CommonUtilLoad {
    private static Long longUtil = new Long(0);

    private static Integer integerUtil = new Integer(0);

    private static Double doubleUtil = new Double(0);
    /**
     * 加载头部数据
     */
    @Pointcut("execution(public * shop4j.controllers.*.*.*(..))")
    public void loadUtils() {
    }

    /**
     * 注入类型转转换工具
     * @param joinPoint
     */
    @After("loadUtils()")
    public void doAfter(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();

        if(!Objects.isNull(args) && args.length != 0){

            Stream.of(args).forEach(arg->{

                if (arg instanceof Model) {

                    Model model = (Model) arg;

                    model.addAttribute("Long",longUtil);//长整形转换工具

                    model.addAttribute("Double",doubleUtil);//长整形转换工具

                    model.addAttribute("Integer",integerUtil);//长整形转换工具
                }
            });
        }
    }
}
