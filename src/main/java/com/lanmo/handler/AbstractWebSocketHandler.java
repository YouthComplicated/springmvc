package com.lanmo.handler;

import org.springframework.web.socket.*;

/**
 *
 */
public class AbstractWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage) {
            handleTextMessage(session, (TextMessage) message);
        }
        else if (message instanceof BinaryMessage) {
            handleBinaryMessage(session, (BinaryMessage) message);
        }
        else if (message instanceof PongMessage) {
            handlePongMessage(session, (PongMessage) message);
        }
        else {
            throw new IllegalStateException("Unexpected WebSocket message type: " + message);
        }
    }
    //用来处理文本消息类型的方法
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }
    //用来处理二进制消息类型的方法
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
    }
    //用来处理Pong消息类型的方法
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
