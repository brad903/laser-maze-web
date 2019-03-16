package lasermaze.support.test;

import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

import static lasermaze.security.HttpSessionUtils.USER_SESSION_KEY;
import static lasermaze.socket.WebSocketSessionUtils.GAME_SESSION_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class WebSocketSessionTest extends BasicAuthAcceptanceTest {

    protected GameRoom gameRoom = GameRoom.create("test방");

    protected WebSocketSession webSocketSession = mock(WebSocketSession.class);

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> map = new HashMap<>();
        gameRoomRepository.save(gameRoom);
        map.put(USER_SESSION_KEY, loginUser);
        map.put(GAME_SESSION_KEY, gameRoom.getId());
        when(webSocketSession.getAttributes()).thenReturn(map);
    }
}
