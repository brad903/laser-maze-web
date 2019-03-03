package lasermaze.socket;

import lasermaze.domain.User;
import lasermaze.security.HttpSessionUtils;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionUtils {
    public static final String GAME_SESSION_KEY = "roomId";

    public static User getUserFromSocket(WebSocketSession session) {
        return (User) session.getAttributes().get(HttpSessionUtils.USER_SESSION_KEY);
    }

    public static String getGameRoomIdFromSocket(WebSocketSession session) {
        return (String) session.getAttributes().get(GAME_SESSION_KEY);
    }
}
