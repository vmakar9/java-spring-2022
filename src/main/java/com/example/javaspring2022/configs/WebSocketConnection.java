package com.example.javaspring2022.configs;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

public class WebSocketConnection extends TextWebSocketHandler {

    private Map<String,WebSocketSession> sessionMap = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connection done " + session.getId());
        sessionMap.put(session.getId(),session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("connection close " + session.getId());

        Set<Map.Entry<String,WebSocketSession>> entries = sessionMap.entrySet();
        Iterator<Map.Entry<String, WebSocketSession>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,WebSocketSession> next = iterator.next();
            if(next.getKey().equals(session.getId())){
                iterator.remove();
//                session.close();
            }
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
       sessionMap.forEach((s, webSocketSession) -> {
           try {
               webSocketSession.sendMessage(new TextMessage(message.getPayload()+ " " + new Date(System.currentTimeMillis())));
           } catch (IOException e) {
               System.out.println("session " + session.getId()+ "already closed");

           }
       });

    }
}
