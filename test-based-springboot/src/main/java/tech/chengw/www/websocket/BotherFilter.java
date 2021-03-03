package tech.chengw.www.websocket;

import org.springframework.integration.annotation.Filter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/28
 **/
@Component
public class BotherFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    public static void main(String[] args) {
        Integer i = null;
        boolean b = i == 1;
    }
}
