package lasermaze.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameRoomRepository {
    private Map<String, GameRoom> gameRooms = new HashMap<>();

    public void save(GameRoom gameRoom) {
        gameRooms.put(gameRoom.getId(), gameRoom);
    }

    public GameRoom getGameRoom(String id) {
        return gameRooms.get(id);
    }

    public void removeGame(WebSocketSession session, ObjectMapper objectMapper) {
        gameRooms.keySet().stream().forEach(key -> gameRooms.get(key).remove(session, objectMapper));
    }
}
