package shop4j.controllers.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop4j.annotions.shop.dataload.HeadDataLoad;

/**
 * @Author: weixuedong
 * @Date: 2018/5/30 14:24
 * @Description:登陆
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    @HeadDataLoad
    public String loginView(Model model){
        return "shop/login/login";
    }
}
