package lasermaze.domain;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.socket.MessageSendUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class GameRoom {
    private String id;
    private String name;
    private Map<User, WebSocketSession> sessions = new HashMap<>();

    public static GameRoom create(String name) {
        GameRoom created = new GameRoom();
        created.id = UUID.randomUUID().toString();
        created.name = name;
        return created;
    }

    public void join(User user, WebSocketSession session) {
        sessions.put(user, session);
    }

    public void sendPlayerList(ObjectMapper objectMapper) {
        send(sessions.keySet(), objectMapper);
    }

    public <T> void send(T messageObject, ObjectMapper objectMapper) {
        try {
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
            sessions.values().parallelStream()
                    .forEach(session -> MessageSendUtils.sendMessage(session, message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void remove(WebSocketSession target, ObjectMapper objectMapper) {
        String targetId = target.getId();
        Optional<User> removableUser = sessions.keySet().parallelStream()
                .filter(user -> sessions.get(user).getId().equals(targetId))
                .findFirst();

        if (removableUser.isPresent()) {
            sessions.remove(removableUser.get());
            sendPlayerList(objectMapper);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}