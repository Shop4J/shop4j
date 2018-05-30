package shop4j.controllers.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop4j.annotions.shop.dataload.HeadDataLoad;
import shop4j.vo.login.LoginVO;

import javax.validation.constraints.NotNull;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 14:24
 * @Description:登陆
 */
@Controller
@RequestMapping("/login")
public class LoginController{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @GetMapping
    @HeadDataLoad
    public String loginView(Model model){
        return "shop/login/login";
    }

    @PostMapping("/login")
    public String login(@NotNull LoginVO loginVO){
        return null;
    }
}
