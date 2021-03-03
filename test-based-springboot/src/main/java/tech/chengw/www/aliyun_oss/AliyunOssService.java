package tech.chengw.www.aliyun_oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/9
 **/
@Service
public class AliyunOssService {

    @Autowired
    private OSS ossClient;
    @Autowired
    private OssProperty ossProperty;

    /**
     * 开机时调用，断点续传
     * @param directoryPath
     */
    public void uploadRest(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
//            log.err("")
        }
        for (File file : directory.listFiles()) {
            try {
                upload(file.getPath());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * 上传文件
     * @param filePath
     * @throws Throwable
     */
    public void upload(String filePath) throws Throwable {
        File file = new File(filePath);
        String fileKey = ossProperty.getDeviceCertificateDirectoryName() + "/" + file.getName();

        // 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(ossProperty.getBucketName(), fileKey);

        // 指定上传的本地文件。
        uploadFileRequest.setUploadFile(filePath);
        // 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
        // 指定上传的分片大小。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
        // 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
//        // 记录本地分片上传结果的文件。使用默认值即可
//        uploadFileRequest.setCheckpointFile("<yourCheckpointFile>");
        // 文件的元数据。
        ObjectMetadata meta = new ObjectMetadata();
        //  指定上传的内容类型。默认application/octet-stream会下载文件，text/plain会尝试直接打开
        meta.setContentType("text/plain");
        //  文件上传时设置访问权限ACL。
        meta.setObjectAcl(CannedAccessControlList.Private);
        uploadFileRequest.setObjectMetadata(meta);

        // 设置上传成功回调，参数为Callback类型。
//        uploadFileRequest.setCallback(new Callback());

        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);

        // 上传成功后删除文件，每次启动查看是否有残余文件，有就是需要续传的文件
        file.delete();
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 生成临时url
     * @param key
     * @return
     */
    public URL generatePresignedUrl(String key) {
        //生成临时url
        return ossClient.generatePresignedUrl(ossProperty.getBucketName(),key,
                new Date(System.currentTimeMillis() + ossProperty.getExpireSecond() * 1000));
    }
}
