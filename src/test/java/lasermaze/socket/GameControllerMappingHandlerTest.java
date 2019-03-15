package lasermaze.socket;

import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.domain.User;
import lasermaze.dto.MessageDto;
import lasermaze.security.HttpSessionUtils;
import lasermaze.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

import static lasermaze.socket.WebSocketSessionUtils.GAME_SESSION_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

public class GameControllerMappingHandlerTest extends AcceptanceTest {
    private static final Logger log = getLogger(GameControllerMappingHandlerTest.class);

    private WebSocketSession webSocketSession = mock(WebSocketSession.class);

    private User user = new User("brad903", "1234", "브래드");
    private GameRoom gameRoom = GameRoom.create("test방");

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @Autowired
    private GameControllerMappingHandler gameControllerMappingHandler;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> map = new HashMap<>();
        gameRoomRepository.save(gameRoom);
        map.put(HttpSessionUtils.USER_SESSION_KEY, user);
        map.put(GAME_SESSION_KEY, gameRoom.getId());
        when(webSocketSession.getAttributes()).thenReturn(map);
    }

    @Test
    public void invoke() throws Exception {
        MessageType messageType = new MessageType("/join", RequestMethod.POST);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageType(messageType);
        messageDto.setWebSocketSession(webSocketSession);
        gameControllerMappingHandler.invoke(messageDto);
    }
}