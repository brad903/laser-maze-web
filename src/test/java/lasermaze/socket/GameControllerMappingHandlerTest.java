package lasermaze.socket;

import lasermaze.dto.MessageDto;
import lasermaze.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

public class GameControllerMappingHandlerTest extends AcceptanceTest {

    @Autowired
    private GameControllerMappingHandler gameControllerMappingHandler;

    @Test
    public void invoke() throws Exception {
        MessageType messageType = new MessageType("/join", RequestMethod.POST);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageType(messageType);
        gameControllerMappingHandler.invoke(messageDto);
    }
}