package lasermaze.socket;

import lasermaze.domain.User;
import lasermaze.dto.MessageDto;
import lasermaze.support.test.BasicAuthAcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class UserHandlerMethodArgumentResolverTest extends BasicAuthAcceptanceTest {

    @Autowired
    private UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;

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
        assertThat(userHandlerMethodArgumentResolver.resolveArgument(messageDto)).isEqualTo(loginUser);
    }
}