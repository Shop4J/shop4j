package shop4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 11:45
 * @Description:图片转发
 */
@Configuration
public class ImageConfiguration implements WebMvcConfigurer {
    @Autowired
    private ImageConfig imageConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        String osName = System.getProperty("os.name");//操作系统名称
        if(osName.toLowerCase().contains("win")) {
            registry.addResourceHandler(imageConfig.getHandler()).addResourceLocations("file:"+imageConfig.getLocation()[0]);
        }else {
            registry.addResourceHandler(imageConfig.getHandler()).addResourceLocations("file:"+imageConfig.getLocation()[1]);
        }
    }
}
