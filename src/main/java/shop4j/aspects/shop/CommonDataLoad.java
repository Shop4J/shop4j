package shop4j.aspects.shop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import shop4j.models.sets.WebInfo;
import shop4j.services.login.LoginService;
import shop4j.services.sets.WebInfoService;
import shop4j.vo.login.UserDetailVO;

import java.util.Objects;
import java.util.stream.Stream;


/**
 * @Author: weixuedong
 * @Date: 2018/4/21 16:13
 * @Description:商城公共数据加载，比如头资源,尾资源等
 */
@Aspect
@Order(5)
@Component
public class CommonDataLoad {
    @Autowired
    private WebInfoService webInfoService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;
    /**
     * 加载头部数据
     */
    @Pointcut("execution(public * shop4j.controllers.shop.*.*(..)) && @annotation(shop4j.annotions.shop.dataload.HeadDataLoad)")
    public void loadHeadData() {
    }

    @After("loadHeadData()")
    public void doAfter(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();

        if(!Objects.isNull(args) && args.length != 0){

            Stream.of(args).forEach(arg->{

                if (arg instanceof Model) {

                    Model model = (Model) arg;

                    log.debug("加载商城公共页面头信息...");

                    WebInfo webInfo = webInfoService.getWebRoot();

                    model.addAttribute("webInfo",webInfo);//站点信息

                    UserDetailVO userVO = loginService.getCurrentLogin();

                    model.addAttribute("user",userVO);

                    log.debug("加载商城公共页面头信息完成");
                    return;
                }
            });
        }
    }

}
