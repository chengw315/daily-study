package tech.chengw.www.aliyun_oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/9
 **/
@ConfigurationProperties(prefix = "oss")
@Data
public class OssProperty {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private String accessKeyId = "<yourAccessKeyId>";
    private String accessKeySecret = "<yourAccessKeySecret>";
    private String bucketName = "<yourBucket>";
    private String deviceCertificateDirectoryName = "<yourBucket>";
    //临时url的过期时间
    private Long expireSecond = 300L;
}
