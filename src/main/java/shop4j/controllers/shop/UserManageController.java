package shop4j.controllers.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop4j.annotions.shop.dataload.HeadDataLoad;

/**
 * @Author: weixuedong
 * @Date: 2018/5/31 16:50
 * @Description:我的
 */
@Controller
@RequestMapping("/user")
public class UserManageController {
    @GetMapping("/index")
    @HeadDataLoad
    public String index(Model model){
        return "shop/my/index";
    }
}
