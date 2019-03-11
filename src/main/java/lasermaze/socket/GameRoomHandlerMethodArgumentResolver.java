package lasermaze.socket;

import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class GameRoomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @Override
    public boolean supportsParameter(Class<?> parameterType) {
        return parameterType.equals(GameRoom.class);
    }

    @Override
    public Object resolveArgument(MessageDto messageDto) {
        WebSocketSession webSocketSession = messageDto.getWebSocketSession();
        String id = WebSocketSessionUtils.getGameRoomIdFromSocket(webSocketSession);
        return gameRoomRepository.getGameRoom(id);
    }
}
