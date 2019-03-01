package lasermaze.domain;

import org.springframework.stereotype.Component;

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
}
