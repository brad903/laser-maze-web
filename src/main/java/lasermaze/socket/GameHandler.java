package lasermaze.socket;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.domain.*;
import lasermaze.security.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(GameHandler.class);

    private final ObjectMapper objectMapper;
    private final GameRoomRepository repository;

    @Autowired
    public GameHandler(ObjectMapper objectMapper, GameRoomRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        Map<String, Object> parsedMessage = objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {});

        GameRoom gameRoom = repository.getGameRoom(String.valueOf(parsedMessage.get("roomId")));
        User user = objectMapper.convertValue(parsedMessage.get("user"), User.class);

        if (parsedMessage.get("messageType").equals("JOIN")) {
            gameRoom.join(Player.createPlayer(user, session));
            gameRoom.sendPlayerList(objectMapper);
        }

        if (parsedMessage.get("messageType").equals("PLAY")) {
            CommandMessage commandMessage = objectMapper.readValue(payload, CommandMessage.class);
            gameRoom.send(commandMessage, objectMapper);
        }

        if (parsedMessage.get("messageType").equals("READY")) {
            gameRoom.getPlayer(user).pushReady();
            gameRoom.sendPlayerList(objectMapper);
            if(gameRoom.isAllReady()) {
                //.send();
            }
        }

        // readyBtn 비활성화
        // Player1 시작 가능 셋팅,  Player2 비활성화

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.debug("Session Remove");
        repository.removeGame(session, objectMapper);
    }
}
