package tech.chengw.www.websocket;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * description websocket 消息处理器
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/8
 **/
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //接收到的消息体
        PayloadIn payloadIn = JSON.parseObject(message.getPayload(), PayloadIn.class);

        System.out.println("接收到消息" + payloadIn);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String token = session.getUri().toString().split("\\?token=")[1];
        //缓存session
        //将session缓存下来可主动发送消息
        //发送消息
        session.sendMessage(new TextMessage(JSON.toJSONString(new PayloadOut()
                .setMessage("上报消息1"))));
    }
}
