package shop4j.config.login;

import base.util.string.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: weixuedong
 * @Date: 2018/6/2 16:36
 * @Description:
 */
public class CustomSavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        String back = (String)request.getSession().getAttribute("back");
        if (StringUtil.isNotEmpty(back)) {
                response.getWriter().write(back);
        }else{
            request.getRequestDispatcher("/index").forward(request, response);
        }

    }
}
