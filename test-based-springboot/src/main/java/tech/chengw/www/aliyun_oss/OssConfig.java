package tech.chengw.www.aliyun_oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/9
 **/
@Configuration
@EnableConfigurationProperties(OssProperty.class)
public class OssConfig {

    @Autowired
    private OssProperty ossProperty;


    @Bean
    public OSS ossClient() {
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(ossProperty.getEndpoint(), ossProperty.getAccessKeyId(), ossProperty.getAccessKeySecret());
    }
}
