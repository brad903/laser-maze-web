package lasermaze.dto;

import lasermaze.domain.message.*;

public class RequestDto {

    private MessageType messageType;
    private int row;
    private int col;
    private int commandNumber;

    public RequestDto() {
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
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

    public Message createMessage() {
        if(messageType == MessageType.JOIN) return new JoinMessage();
        if(messageType == MessageType.READY) return new ReadyMessage();
        if(messageType == MessageType.PLAY) return new CommandMessage(row, col, commandNumber);
        throw new IllegalArgumentException();
    }
}
