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

    @Autowired
    private GameRoomRepository roomRepository;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        GameRoom gameRoom = roomRepository.getGameRoom(WebSocketSessionUtils.getGameRoomIdFromSocket(session));
        User user = WebSocketSessionUtils.getUserFromSocket(session);

        String payload = message.getPayload();
        log.debug("Payload : {}", payload);
        MessageDto messageDto = new ObjectMapper().readValue(payload, MessageDto.class);
        Message parsedMessage = messageDto.createMessage();

        parsedMessage.process(gameRoom, user, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.debug("Session Remove");
        roomRepository.removeGame(session);
    }
}
