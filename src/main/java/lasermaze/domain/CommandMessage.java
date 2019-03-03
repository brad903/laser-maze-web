package lasermaze.domain;

import org.springframework.web.socket.WebSocketSession;

public class CommandMessage implements Message {

    private int row;
    private int col;
    private int commandNumber;

    public CommandMessage(int row, int col, int commandNumber) {
        this.row = row;
        this.col = col;
        this.commandNumber = commandNumber;
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

    @Override
    public void process(GameRoom gameRoom, User user, WebSocketSession session) {

    }

    @Override
    public String toString() {
        return "CommandMessage{" +
                ", row=" + row +
                ", col=" + col +
                ", commandNumber=" + commandNumber +
                '}';
    }
}
