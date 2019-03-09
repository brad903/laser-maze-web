package lasermaze.dto;

import lasermaze.socket.MessageType;

public class MessageDto {

    private MessageType messageType;

    public MessageDto() {
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

}
