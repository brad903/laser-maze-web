package lasermaze.dto;

import lasermaze.socket.MessageType;
import org.springframework.web.socket.WebSocketSession;

public class MessageDto {

    private MessageType messageType;

    private WebSocketSession webSocketSession;

    public MessageDto() {
    }

    public MessageDto(MessageType messageType, WebSocketSession webSocketSession) {
        this.messageType = messageType;
        this.webSocketSession = webSocketSession;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageType=" + messageType +
                ", webSocketSession=" + webSocketSession +
                '}';
    }
}
