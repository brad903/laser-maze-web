package lasermaze.socket;


import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class GameHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(GameHandler.class);

    private final ObjectMapper objectMapper;
    private final GameRoomRepository roomRepository;

    @Autowired
    public GameHandler(ObjectMapper objectMapper, GameRoomRepository roomRepository) {
        this.objectMapper = objectMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        GameRoom gameRoom = roomRepository.getGameRoom(WebSocketSessionUtils.getGameRoomIdFromSocket(session));
        User user = WebSocketSessionUtils.getUserFromSocket(session);

        String payload = message.getPayload();
        Message parsedMessage = objectMapper.readValue(payload, Message.class);


        if (parsedMessage.getMessageType().equals(MessageType.JOIN)) {
            gameRoom.join(Player.createPlayer(user, session));
            gameRoom.sendPlayerList(objectMapper);
        }

        if (parsedMessage.getMessageType().equals("PLAY")) {
            CommandMessage commandMessage = objectMapper.readValue(payload, CommandMessage.class);
            gameRoom.send(commandMessage, objectMapper);
        }

        if (parsedMessage.getMessageType().equals("READY")) {
            gameRoom.getPlayer(user).pushReady();
            gameRoom.sendPlayerList(objectMapper);
            if (gameRoom.isAllReady()) {
                //.send();
            }
        }

        // readyBtn 비활성화
        // Player1 시작 가능 셋팅,  Player2 비활성화

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.debug("Session Remove");
        roomRepository.removeGame(session, objectMapper);
    }
}
