package shop4j.config.login;

import lombok.Data;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: weixuedong
 * @Date: 2018/6/1 9:49
 * @Description:重新定义表单数据
 */
@Data
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    /**
     * 验证码
     */
    private final String token;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        token = request.getParameter("token");
    }

}
