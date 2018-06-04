package shop4j.aspects.shop;

import base.util.collections.CollectionUtil;
import base.util.string.StringUtil;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import shop4j.models.sets.WebInfo;
import shop4j.services.login.LoginService;
import shop4j.services.sets.WebInfoService;
import shop4j.vo.login.UserDetailVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

            List<Object> models = Stream.of(args).filter(arg -> arg instanceof Model).collect(Collectors.toList());

            if(CollectionUtil.isNotEmpty(models)){
                RequestAttributes ra = RequestContextHolder.getRequestAttributes();

                ServletRequestAttributes sra = (ServletRequestAttributes) ra;

                HttpServletRequest request = sra.getRequest();

                StringBuffer url = request.getRequestURL();

                String queryString = request.getQueryString();

                Model model = (Model) models.get(0);

                log.debug("加载商城公共页面头信息...");

                WebInfo webInfo = webInfoService.getWebRoot();

                model.addAttribute("webInfo",webInfo);//站点信息

                UserDetailVO userVO = loginService.getCurrentLogin();

                model.addAttribute("user",userVO);

                /**
                 * 回跳URL,手动点击登陆需要
                 */
                model.addAttribute("url", StringUtil.isEmpty(queryString)?url.toString():url.append("?").append(queryString).toString());

                log.debug("加载商城公共页面头信息完成");


            }


        }
    }

}
