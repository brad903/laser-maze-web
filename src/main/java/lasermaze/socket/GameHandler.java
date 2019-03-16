package lasermaze.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.domain.User;
import lasermaze.dto.MessageDto;
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
    private GameControllerMappingHandler gameControllerMappingHandler;

    @Autowired
    private GameRoomRepository roomRepository;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        GameRoom gameRoom = roomRepository.getGameRoom(WebSocketSessionUtils.getGameRoomIdFromSocket(session));
        User user = WebSocketSessionUtils.getUserFromSocket(session);

        String payload = message.getPayload();
//        RequestDto requestDto = new ObjectMapper().readValue(payload, RequestDto.class);
        MessageType messageType = new ObjectMapper().readValue(payload, MessageType.class);
        log.debug("messageDto : {}", messageType);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageType(messageType);
        messageDto.setWebSocketSession(session);
        gameControllerMappingHandler.invoke(messageDto);
//        if(messageDto.isTerminated()) {
//            roomRepository.removeSessionUser(session);
//            return;
//        }
//        Message parsedMessage = messageDto.createMessage();
//
//        parsedMessage.process(gameRoom, user, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        roomRepository.removeSessionUser(session);
    }
}
