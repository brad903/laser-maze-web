package lasermaze.socket;

import lasermaze.domain.GameRoom;
import lasermaze.dto.MessageDto;
import lasermaze.support.test.WebSocketSessionTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRoomHandlerMethodArgumentResolverTest extends WebSocketSessionTest {

    @Autowired
    private GameRoomHandlerMethodArgumentResolver gameRoomHandlerMethodArgumentResolver;

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