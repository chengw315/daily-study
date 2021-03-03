package tech.chengw.www.doc_upload_download;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * description 静态资源配置
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/17
 **/
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    private String parentPath = "/upload/pdf";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String s = System.getProperty("user.dir") + parentPath.replace("/", File.separator) + File.separator;
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + s.replaceAll("//","/"))
                .addResourceLocations("classpath:/pdf/");
    }
}
