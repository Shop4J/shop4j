package shop4j.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.Arrays;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 11:45
 * @Description:图片转发
 */
@Configuration
public class ImageConfiguration implements WebMvcConfigurer {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ImageConfig imageConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        String[] locations = imageConfig.getLocation();
        Arrays.stream(locations).forEach(location-> {
            File file = new File(location);
            if(file.exists()){
                registry.addResourceHandler(imageConfig.getHandler()).addResourceLocations("file:"+location);
                log.info("发现可用图片地址："+location);
                return;
            }
        });
    }
}
