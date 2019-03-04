package lasermaze.domain.message;

import lasermaze.domain.GameRoom;
import lasermaze.domain.User;
import lasermaze.domain.message.Message;
import org.springframework.web.socket.WebSocketSession;

public class ReadyMessage implements Message {
    @Override
    public void process(GameRoom gameRoom, User user, WebSocketSession session) {
        gameRoom.getPlayer(user).pushReady();
        gameRoom.sendPlayerList();
        if (gameRoom.isAllReady()) {
            // 체스말과 사용자 연결
            //
        }
    }
}
