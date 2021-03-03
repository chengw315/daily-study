package tech.chengw.www.websocket;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/8
 **/
@Data
@Accessors(chain = true)
public class PayloadOut {
    private String message;
}
