package shop4j.controllers.shop;

import base.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop4j.annotions.shop.dataload.HeadDataLoad;
import shop4j.enums.LoginStatusEnum;
import shop4j.result.LoginResult;
import shop4j.services.login.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 14:24
 * @Description:登陆
 */
@Controller
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private LoginService loginService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 登录页
     * @param model
     * @return
     */
    @GetMapping
    @HeadDataLoad
    public String loginView(Model model, HttpSession session,String urlBack){
        String backUrl = (String) session.getAttribute("back");
        if(StringUtil.isEmpty(backUrl)){
            backUrl=urlBack;
        }
        model.addAttribute("back",backUrl);
        return "shop/login/login";
    }


    /**
     * 成功JSON
     * @return
     */
    @PostMapping("/success")
    @ResponseBody
    public LoginResult success(){
        LoginResult loginResult = new LoginResult();
        loginResult.setStatus(LoginStatusEnum.Success.getStatus());
        loginResult.setMsg(LoginStatusEnum.Success.getMsg());
        return loginResult;
    }
    /**
     * 成功JSON
     * @return
     */
    @PostMapping("/error")
    @ResponseBody
    public LoginResult error(){
        LoginResult loginResult = new LoginResult();
        loginResult.setStatus(LoginStatusEnum.Success.getStatus());
        loginResult.setMsg(LoginStatusEnum.Success.getMsg());
        return loginResult;
    }
}
