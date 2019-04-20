package com.lanmo.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 *
 * <bean id="MyWebSocketHandler" class="com.xxx.MyWebSocketHandler"/>
 * <websocket:handlers>
 * 	<websocket:mapping handler="MyWebSocketHandler" path="/hello"/>
 * </websocket:handlers>
 */
public class MyWebSocketHandler extends AbstractWebSocketHandler{

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
