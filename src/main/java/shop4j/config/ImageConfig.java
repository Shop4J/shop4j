package shop4j.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: weixuedong
 * @Date: 2018/5/2 15:16
 * @Description: 图片配置文件
 */
@ConfigurationProperties(prefix = "images")
@Data
@Component
public class ImageConfig {

    private String handler;

    /**
     * 第一位是windows路径 第二位是linux路径
     */
    private String[] location;
}
