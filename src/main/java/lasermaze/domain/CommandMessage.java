package lasermaze.domain;

public class CommandMessage {

    private int row;
    private int col;
    private int commandNumber;

    public CommandMessage() {
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
    public String toString() {
        return "CommandMessage{" +
                ", row=" + row +
                ", col=" + col +
                ", commandNumber=" + commandNumber +
                '}';
    }
}
