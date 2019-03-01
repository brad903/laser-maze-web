package lasermaze.domain;


import java.util.UUID;

public class GameRoom {
    private String id;
    private String name;

    private GameRoom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GameRoom create(String userName) {
        return new GameRoom(UUID.randomUUID().toString(), gerateRoomName(userName));
    }

    private static String gerateRoomName(String userName) {
        return userName + "님의 방";
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
