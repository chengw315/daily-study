package tech.chengw.www.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/2
 **/
@RestController
public class SignController {

    @PostMapping("/needSign")
    public String sign() {
        return "success!";
    }
}
