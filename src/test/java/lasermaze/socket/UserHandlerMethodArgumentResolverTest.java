package lasermaze.socket;

import lasermaze.domain.User;
import lasermaze.dto.MessageDto;
import lasermaze.security.HttpSessionUtils;
import lasermaze.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserHandlerMethodArgumentResolverTest extends AcceptanceTest {
    private WebSocketSession webSocketSession = mock(WebSocketSession.class);

    @Autowired
    private UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;

    private User user = new User("brad903", "1234", "브래드");

    @Before
    public void setUp() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(HttpSessionUtils.USER_SESSION_KEY, user);
        when(webSocketSession.getAttributes()).thenReturn(map);
    }

    @Test
    public void isSameClass() {
        Class<?> parameterType = User.class;
        assertThat(parameterType.equals(User.class)).isTrue();
    }

    @Test
    public void supportsParameter() {
        assertThat(userHandlerMethodArgumentResolver.supportsParameter(User.class)).isTrue();
    }

    @Test
    public void resolveArgument() {
        MessageDto messageDto = new MessageDto();
        messageDto.setWebSocketSession(webSocketSession);
        assertThat(userHandlerMethodArgumentResolver.resolveArgument(messageDto)).isEqualTo(user);
    }
}