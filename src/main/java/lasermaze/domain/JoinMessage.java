package lasermaze.domain;

import org.springframework.web.socket.WebSocketSession;

public class JoinMessage implements Message {

    @Override
    public void process(GameRoom gameRoom, User user, WebSocketSession session) {
        gameRoom.join(Player.createPlayer(user, session));
        gameRoom.sendPlayerList();
    }
}
