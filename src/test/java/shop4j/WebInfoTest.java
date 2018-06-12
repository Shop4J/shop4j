package shop4j;

import base.util.random.RandomUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop4j.enums.WebInfoTypeEnum;
import shop4j.models.sets.WebInfo;
import shop4j.services.sets.WebInfoService;
import shop4j.services.user.UserService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

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

    @Autowired
    private UserService userService;
    @Test
    public void testError(){
        List<Runnable> list= new LinkedList<>();
        for(int i=1;i<=50;i++){
            list.add(()->{
                while (true){
                    userService.freezeUser(2);
                    System.out.println(LocalDateTime.now()+",更新一次");
                }
            });
        }
        list.forEach(Runnable::run);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
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
