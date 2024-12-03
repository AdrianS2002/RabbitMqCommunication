package com.example.MonitoringAndCommunicationMicroService.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

        // Add user-specific attributes if needed (e.g., from a query parameter or header)
        String userId = session.getUri().getQuery().split("=")[1]; // Example: Extract userId from query
        session.getAttributes().put("userId", Long.parseLong(userId));

        System.out.println("WebSocket connection established for user: " + userId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    public static Set<WebSocketSession> getSessions() {
        return sessions;
    }
}

