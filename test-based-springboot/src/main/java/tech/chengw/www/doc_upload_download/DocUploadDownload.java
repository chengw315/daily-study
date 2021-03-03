package tech.chengw.www.doc_upload_download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/19
 **/
@Service
public class DocUploadDownload {

    private String parentPath = "/upload/pdf";
    private String docName = "Tracker.pdf";
    private Logger logger = LoggerFactory.getLogger(DocUploadDownload.class);

    @PostConstruct
    public void init() throws IOException {
        String path = fileUploadDirectoryCheck(parentPath) + "/docName.txt";
        if (path.startsWith("//")) {
            path = path.substring("//".length());
        }
        File file = new File(path);
        if (file.exists()) {
            char[] chars = new char[50];
            int read = new FileReader(file).read(chars);
            String lastDocName = String.copyValueOf(chars);
            logger.info("获取到当前的API文档名-{}",lastDocName);
            docName = lastDocName;
        }
    }

    public String getApiDoc() throws Exception {
        String path = System.getProperty("user.dir") + File.separator + parentPath + File.separator + docName;
        if (path.startsWith("//")) {
            path = path.substring("//".length());
        }
        File file = new File(path);
        if (file.exists()) {
            return "/static/" + docName;
        }
        ClassPathResource fileOrigin = new ClassPathResource("pdf/Tracker_Origin.pdf");
        if (fileOrigin.exists()) {
            return "/static/Tracker_Origin.pdf";
        }
        throw new Exception("文档丢失，请联系管理员处理");
    }

    public void upload(HttpServletRequest request, int userType) throws Exception {
        if (userType != 1) {
            throw new Exception("非管理员用户");
        }

        //文件获取
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("file");//只选择一个文件


        if (ObjectUtils.isEmpty(file)) { //没有选择文件
            throw new Exception("未选择文件");
        }

        //获取上传文件类型的扩展名
        String originalFileName = file.getOriginalFilename();
        if (originalFileName.length() < 4 || originalFileName.substring(originalFileName.length()-4).equals(".pdf")) {
            throw new Exception("格式不正确，请上传pdf类型的文件");
        }


        String fileUploadDirectoryCheck = fileUploadDirectoryCheck(parentPath);
        //保存文件到服务器，文件名：文件名头部+文件扩展名
        String newDocName = "Tracker-${System.currentTimeMillis()}.pdf";
        String filePath = "$fileUploadDirectoryCheck/$newDocName";
        File targetFile = new File(filePath);
        if (targetFile.exists() && !targetFile.delete()) {
            throw new Exception("覆盖旧版文档失败，请重试");
        }

        try {
            file.transferTo(targetFile); //将上传文件写到服务器上指定的文件
            docName = newDocName;
            //持久化文档信息
            String path = (fileUploadDirectoryCheck +  "/docName.txt");
            if (path.startsWith("//")) {
                path = path.substring("//".length());
            }
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(newDocName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("文件上传失败，请重试");
        }
    }

    /**
     *  确保目录每一级都存在，不存在则创建
     * @param parentPath 目录路径
     * @return
     */
    private String fileUploadDirectoryCheck(String parentPath) {
        //获取所有文件夹名
        String[] dirs = parentPath.split("/");

        //检查文件夹是否存在，不存在则创建
        StringBuffer filePath = new StringBuffer(System.getProperty("user.dir"));
        for (String dir : dirs) {
            if (ObjectUtils.isEmpty(dir)) {
                continue;
            }
            filePath.append(File.separator).append(dir);
            File fileDir = new File(filePath.toString());
            if (!fileDir.exists() || fileDir.exists() && !fileDir.isDirectory() && !fileDir.mkdir()) {
                return null;
            }
        }
        return filePath.toString();
    }

    /**
     * 直接下载
     * @param response
     */
    public void downLoadDirect(HttpServletResponse response) throws IOException {
        File file = new File("E://dvcHistoryDump0.sql");

        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        //缓存控制，具体实现还要看请求端（一般是前端代码或浏览器）的实现，它实现了就能控制，没实现就不能控制
        //缓存控制1.主流浏览器都会实现
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        //缓存控制2.几乎只有IE实现
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");



        //input 接 output的方法
        //1.input全部到内存中，再写入output 小心OOM
        //2.使用单缓冲区 只用 8192字节 的内存。研究明白BufferedInputStream以后反而不屑于用了，可能这就是男人吧
        // 本地100M/s
        FileInputStream inputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[8192];
        while (true) {
            int read = inputStream.read(bytes);
            if (read == 8192) {
                outputStream.write(bytes);
            } else {
                outputStream.write(bytes,0,read);
                break;
            }
        }

//        //3.循环缓冲（阻塞队列实现），读写两不误
//        LinkedBlockingQueue<byte[]> queue = new LinkedBlockingQueue<>();
//        AtomicBoolean over = new AtomicBoolean(false);
//        //读
//        new Thread(()->{
//            byte[] temp = new byte[8192];
//            while (true) {
//                int read = 0;
//                try {
//                    read = inputStream.read(temp);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (read == 8192) {
//                    queue.offer(temp);
//                } else {
//                    byte[] temp1 = new byte[read];
//                    System.arraycopy(temp,0,temp1,0,read);
//                    queue.offer(temp1);
//                    break;
//                }
//            }
//            //通知写线程已经over了
//            over.set(true);
//        }).run();
//        //写
//        while (!over.get()) {
//            try {
//                outputStream.write(Objects.requireNonNull(queue.poll()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//        //4.现成的循环缓冲
//        new Thread();
//
//
//        //5.管道
//        PipedInputStream
    }
}
