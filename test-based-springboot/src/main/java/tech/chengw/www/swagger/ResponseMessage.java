package tech.chengw.www.swagger;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import tech.chengw.www.MainApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * 数据返回对象
 * 因国际化需求，一些方法和成员已过时，不能在国际化中使用
 * Version: 1.0.0
 * Update: 2017/5/24
 * Created by Leaves on 2017/5/24.
 */
public class ResponseMessage<T> {

    //国际化
    private static MessageSource messageSource = MainApplication.getBean(MessageSource.class);

    @ApiModelProperty(value = "响应码",example = "8001")
    private String code;
    @ApiModelProperty(value = "响应描述",example = "操作成功")
    private String msg;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public static final String CODE_SUCCESS = "8001";
    public static final String MSG_SUCCESS = "操作成功";

    public static final String CODE_ERROR = "4001";
    public static final String MSG_ERROR = "操作失败";


    public static Map<String, String> map = new HashMap<>();

    static {
        map.put(CODE_SUCCESS, MSG_SUCCESS);
        map.put(CODE_ERROR, MSG_ERROR);
    }

    public ResponseMessage(String code) {
        this.code = code;
        this.msg = messageSource.getMessage(code, null, code, LocaleContextHolder.getLocale());
        this.data = null;
    }

    public ResponseMessage(String code, T data) {
        this.code = code;
        this.msg = messageSource.getMessage(code, null, code, LocaleContextHolder.getLocale());
        this.data = data;
    }


}
