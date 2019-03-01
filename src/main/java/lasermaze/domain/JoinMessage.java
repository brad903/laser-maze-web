package lasermaze.domain;

public class JoinMessage {
    private User user;
    private String roomId;

    public JoinMessage() {

    }

    public JoinMessage(User user, String roomId) {
        this.user = user;
        this.roomId = roomId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "JoinMessage{" +
                "user=" + user +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
