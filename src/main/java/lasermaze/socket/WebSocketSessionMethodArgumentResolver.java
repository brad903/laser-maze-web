package lasermaze.socket;

import lasermaze.dto.MessageDto;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketSessionMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(Class<?> parameterType) {
        return parameterType.equals(WebSocketSession.class);
    }

    @Override
    public Object resolveArgument(MessageDto messageDto) {
        return messageDto.getWebSocketSession();
    }
}
