package components;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final HashMap<String, WebSocketSession> WEB_SOCKET_SESSIONS = new HashMap<>();

    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {

        if (session != null) {
            WEB_SOCKET_SESSIONS.remove(session.getId());
        }
        
        removeDisconnectedSessions();

    }

    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        
        if (session != null) {
            WEB_SOCKET_SESSIONS.remove(session.getId());
        }
        
        removeDisconnectedSessions();
    }

    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        WEB_SOCKET_SESSIONS.put(session.getId(), session);
    }

    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

    
    public void sendText(final String text) throws IOException {

        if (text == null || text.trim().length() < 1) {
            throw new IllegalArgumentException("text is null or empty");
        }

        removeDisconnectedSessions();
        
        for (Map.Entry<String, WebSocketSession> entry : WEB_SOCKET_SESSIONS.entrySet()) {
            WebSocketSession webSocketSession = entry.getValue();
            if (webSocketSession != null && webSocketSession.isOpen()) {
                webSocketSession.sendMessage(
                        new TextMessage(text.getBytes())
                );
            }
        }

    }

    
    private void removeDisconnectedSessions() {
        WEB_SOCKET_SESSIONS.entrySet().forEach((entry) -> {
            String key = entry.getKey();
            WebSocketSession webSocketSession = entry.getValue();
            if (!(key == null)) {
                if (webSocketSession == null || !webSocketSession.isOpen()) {
                    WebSocketHandler.WEB_SOCKET_SESSIONS.remove(key);
                }
            }
        });
    }

}
