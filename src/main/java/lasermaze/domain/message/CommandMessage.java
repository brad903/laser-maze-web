package lasermaze.domain.message;

import lasermaze.domain.GameRoom;
import lasermaze.domain.User;
import lasermaze.domain.game.Command;
import lasermaze.domain.game.piece.common.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

public class CommandMessage implements Message {

    private static final Logger log = LoggerFactory.getLogger(CommandMessage.class);

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

    public Command _toCommand() {
        return new Command(new Point(row, col), commandNumber);
    }

    @Override
    public void process(GameRoom gameRoom, User user, WebSocketSession session) {
        log.debug("Row : {}, Col : {}, Command : {}, GameRoom : {}, User : {}", row, col, commandNumber, gameRoom, user);
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
