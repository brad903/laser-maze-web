package lasermaze.domain.message;

import lasermaze.domain.GameRoom;
import lasermaze.domain.User;
import org.springframework.web.socket.WebSocketSession;

public interface Message {
    void process(GameRoom gameRoom, User user, WebSocketSession session);
}
