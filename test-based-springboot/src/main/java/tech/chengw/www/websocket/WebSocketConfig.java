package tech.chengw.www.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/8
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new WebSocketHandler(),"debug")
                //跨域
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketInception());
    }
}
