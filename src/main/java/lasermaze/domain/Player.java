package lasermaze.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.socket.WebSocketSession;

public class Player {

    private User user;
    private WebSocketSession webSocketSession;
    private boolean ready;

    public Player() {
    }

    public static Player createPlayer (User user, WebSocketSession webSocketSession) {
        Player player = new Player();
        player.user = user;
        player.webSocketSession = webSocketSession;

        return player;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean hasSameSession(WebSocketSession target) {
        return this.webSocketSession.getId().equals(target.getId());
    }

    public void pushReady() {
        ready = !ready;
    }

    public boolean isSameUser(User user) {
        return this.user.equals(user);
    }

    @Override
    public String toString() {
        return "Player{" +
                "user=" + user +
                ", webSocketSession=" + webSocketSession +
                ", ready=" + ready +
                '}';
    }

    public String getUserId() {
        return user.getUserId();
    }
}
