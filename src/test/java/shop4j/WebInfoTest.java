package shop4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.WebInfoTypeEnum;
import shop4j.models.sets.WebInfo;
import shop4j.services.sets.WebInfoService;

/**
 * @Author: weixuedong
 * @Date: 2018/4/17 18:45
 * @Description:站点信息测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebInfoTest {

    @Autowired
    private WebInfoService webInfoService;

    @Test
    public void testWebInfoAdd(){
        WebInfo webInfo = new WebInfo();
        webInfo.setLogoUrl("images/icons/logo.jpg");
        webInfo.setName("小衣精");
        webInfo.setType(WebInfoTypeEnum.Shop.getType());
        webInfoService.add(webInfo);
    }

    @Test
    public void testGetWebRoot(){

        WebInfo webInfo = webInfoService.getWebRoot();
        System.out.println(webInfo);
    }
}
