package tech.chengw.www.doc_upload_download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/13
 **/
@RestController
public class DocDownLoadController {

    @Autowired
    private DocUploadDownload docUploadDownload;

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        docUploadDownload.downLoadDirect(response);
    }
}
