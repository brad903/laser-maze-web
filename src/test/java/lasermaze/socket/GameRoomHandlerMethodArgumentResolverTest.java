package lasermaze.socket;

import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.dto.MessageDto;
import lasermaze.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

import static lasermaze.socket.WebSocketSessionUtils.GAME_SESSION_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameRoomHandlerMethodArgumentResolverTest extends AcceptanceTest {
    private WebSocketSession webSocketSession = mock(WebSocketSession.class);

    @Autowired
    private GameRoomHandlerMethodArgumentResolver gameRoomHandlerMethodArgumentResolver;

    @Autowired
    private GameRoomRepository gameRoomRepository;

    GameRoom gameRoom = GameRoom.create("test방");

    @Before
    public void setUp() throws Exception {
        gameRoomRepository.save(gameRoom);
        Map<String, Object> map = new HashMap<>();
        map.put(GAME_SESSION_KEY, gameRoom.getId());
        when(webSocketSession.getAttributes()).thenReturn(map);
    }

    @Test
    public void isSameClass() {
        Class<?> parameterType = GameRoom.class;
        assertThat(parameterType.equals(GameRoom.class)).isTrue();
    }

    @Test
    public void supportsParameter() {
        assertThat(gameRoomHandlerMethodArgumentResolver.supportsParameter(GameRoom.class)).isTrue();
    }

    @Test
    public void resolveArgument() {
        MessageDto messageDto = new MessageDto();
        messageDto.setWebSocketSession(webSocketSession);
        assertThat(gameRoomHandlerMethodArgumentResolver.resolveArgument(messageDto)).isEqualTo(gameRoom);
    }

}