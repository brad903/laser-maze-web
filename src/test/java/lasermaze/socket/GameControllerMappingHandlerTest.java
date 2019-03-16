package lasermaze.socket;

import lasermaze.dto.MessageDto;
import lasermaze.support.test.WebSocketSessionTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

public class GameControllerMappingHandlerTest extends WebSocketSessionTest {
    private static final Logger log = getLogger(GameControllerMappingHandlerTest.class);

    @Autowired
    private GameControllerMappingHandler gameControllerMappingHandler;

    @Test
    public void invoke() throws Exception {
        MessageType messageType = new MessageType("/join", RequestMethod.POST);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageType(messageType);
        messageDto.setWebSocketSession(webSocketSession);
        gameControllerMappingHandler.invoke(messageDto);
    }
}