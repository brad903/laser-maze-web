package lasermaze.socket;

import lasermaze.domain.User;
import lasermaze.dto.MessageDto;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(Class<?> parameterType) {
        return parameterType.equals(User.class);
    }

    @Override
    public Object resolveArgument(MessageDto messageDto) {
        WebSocketSession webSocketSession = messageDto.getWebSocketSession();
        return WebSocketSessionUtils.getUserFromSocket(webSocketSession);
    }
}
