package lasermaze.domain;

public class CommandMessage {

    private String roomId;
    private int row;
    private int col;
    private int commandNumber;
    private User user;

    public CommandMessage() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommandMessage{" +
                "roomId='" + roomId + '\'' +
                ", row=" + row +
                ", col=" + col +
                ", commandNumber=" + commandNumber +
                ", user=" + user +
                '}';
    }
}
