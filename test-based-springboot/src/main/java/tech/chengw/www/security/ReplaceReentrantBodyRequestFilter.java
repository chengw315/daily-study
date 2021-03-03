package tech.chengw.www.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * description 替换request为可重复读取body流的reentrantBodyRequest
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/9/16
 **/
@Component
@Order(0)
public class ReplaceReentrantBodyRequestFilter extends HttpFilter {

    private Logger logger = LoggerFactory.getLogger(ReplaceReentrantBodyRequestFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("开始替换request为可重复读取body的request");
        ReentrantBodyRequest reentrantBodyRequest = new ReentrantBodyRequest(request);
        chain.doFilter(reentrantBodyRequest,response);
    }

    /**
     * 可重复读取body的HttpServletRequest包装类
     */
    class ReentrantBodyRequest extends HttpServletRequestWrapper{

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public ReentrantBodyRequest(HttpServletRequest request) throws IOException {
            super(request);
            InputStream bodyInputStream = request.getInputStream();
            body = new byte[bodyInputStream.available()];
            bodyInputStream.read(body);
        }

        private byte[] body;

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new MyServletInputStream(new ByteArrayInputStream(body));
        }

        /**
         * 可装载byteArrayInputStream的ServletInputStream
         * 可装载byteArrayInputStream 是 这个类的唯一作用
         */
        private class MyServletInputStream extends ServletInputStream {
            public MyServletInputStream(ByteArrayInputStream byteArrayInputStream) {
                inputStream = byteArrayInputStream;
            }

            private InputStream inputStream;
            @Override
            public boolean isFinished() {
                try {
                    return inputStream.available() == 0;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        }
    }
}
