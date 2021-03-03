package tech.chengw.www.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/2
 **/
@Component
@WebFilter(filterName = "SignCheckFilter", urlPatterns = {"/needSign*"})
@Order(1)
public class SignCheckFilter extends HttpFilter {

    @Autowired
    private RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(SignCheckFilter.class);

    private static final String REDIS_SIGN_KEY = "sign:";
    private static final String APP_SECRET = "junweicheng";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("进入签名认证");
        //所需的所有信息
        String appId = request.getHeader("appId");
        String sign = request.getHeader("sign");
        String timeStamp = request.getHeader("timestamp");

        if (System.currentTimeMillis() - Long.valueOf(timeStamp) > 20 * 1000) {
            logger.error("签名过期");
            return;
        }

        StringBuilder body = new StringBuilder();
        //获取body
        BufferedReader br = request.getReader();
        String str;
        while((str = br.readLine()) != null){
            body.append(str);
        }
        //校验签名
        if (!checkSign(appId,APP_SECRET,sign,timeStamp,body)) {
            logger.error("签名校验失败");
            return;
        }

        chain.doFilter(request,response);
    }

    private boolean checkSign(String appId, String appSecret, String sign, String timestamp, StringBuilder body) {
        //redis有此时间戳-签名，说明已被调用过
        if (!redisTemplate.opsForValue().setIfAbsent(REDIS_SIGN_KEY + appId + "-" + sign + "-" + timestamp,"xx",20, TimeUnit.SECONDS)) {
            logger.error("签名重复！appId-{},请求签名-{},timestamp-{},body-{}",appId,appSecret,sign,timestamp,body);
            return false;
        }
        String calculatedSign = DigestUtils.md5Hex(String.format("%s%s%s%s",
                appId, appSecret, body, timestamp).getBytes());
        logger.info("appId-{},请求签名-{},timestamp-{},body-{},计算出的校验码-{}",appId,appSecret,sign,timestamp,body,calculatedSign);
        return calculatedSign.equals(sign);
    }

}
