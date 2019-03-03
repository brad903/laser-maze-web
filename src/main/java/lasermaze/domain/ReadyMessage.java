package lasermaze.domain;

import org.springframework.web.socket.WebSocketSession;

public class ReadyMessage implements Message {
    @Override
    public void process(GameRoom gameRoom, User user, WebSocketSession session) {
        gameRoom.getPlayer(user).pushReady();
        gameRoom.sendPlayerList();
        if (gameRoom.isAllReady()) {
            //.send();
        }
    }
}
