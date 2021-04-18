package tech.chengw.www;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.chengw.www.aliyun_oss.AliyunOssService;
import tech.chengw.www.aliyun_oss.OssProperty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/27
 **/
@SpringBootTest
public class OssTest {
    private final String pathname = "test.xls";
    @Autowired
    private AliyunOssService ossService;
    @Autowired
    private OssProperty ossProperty;

    @BeforeAll
    public void before() throws IOException {
        //生成文件
        File file = new File(pathname);
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student().setName("cwj").setBirthday(new Date()).setSex(1));
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams(), Student.class, students);
        sheets.write(new FileOutputStream(file));
    }
    @Test
    public void testUpload() throws Throwable {
        //上传
        ossService.upload(pathname);
        //生成临时URL
        URL url = ossService.generatePresignedUrl(new File(ossProperty.getDeviceCertificateDirectoryName() + "/" + pathname).getName());
        String s = url.toString();
        //长度限制
//        Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome " + s});
    }

}
