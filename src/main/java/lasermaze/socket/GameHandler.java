package lasermaze.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.domain.GameRoomRepository;
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
        String payload = message.getPayload();
        MessageType messageType = new ObjectMapper().readValue(payload, MessageType.class);
        MessageDto messageDto = messageType._toMessageDto(session);
        gameControllerMappingHandler.invoke(messageDto);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        roomRepository.removeSessionUser(session);
    }
}
