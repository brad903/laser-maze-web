package lasermaze.domain;

import org.springframework.web.socket.WebSocketSession;

public interface Message {
    void process(GameRoom gameRoom, User user, WebSocketSession session);
}
