package shop4j.config.login;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: weixuedong
 * @Date: 2018/6/2 17:03
 * @Description:登陆页面处理跳转，用来存储之前的拦截信息进行重写
 */
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(final HttpServletRequest request,
                                                     final HttpServletResponse response, final AuthenticationException e) {
        try{
            //设置  session属性--登录成功后要 要跳转的URL
            request.getSession().setAttribute("back",buildHttpReturnUrlForRequest(request)==null?"/":buildHttpReturnUrlForRequest(request));
        }catch(Exception ex){
        }

        return getLoginFormUrl();
    }
    //获取 当前被 （登录前）被拦截的URL
    protected String buildHttpReturnUrlForRequest(HttpServletRequest request)
            throws IOException, ServletException {
        RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
        urlBuilder.setScheme(request.getScheme());
        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setPort(request.getServerPort());
        urlBuilder.setContextPath(request.getContextPath());
        urlBuilder.setServletPath(request.getServletPath());
        urlBuilder.setPathInfo(request.getPathInfo());
        urlBuilder.setQuery(request.getQueryString());
        String url = urlBuilder.getUrl();
        return url;
    }
}
