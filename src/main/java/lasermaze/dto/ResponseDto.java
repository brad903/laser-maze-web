package lasermaze.dto;

import lasermaze.domain.message.MessageType;

public class ResponseDto<T> {
    private MessageType messageType;
    private T messageObject;

    public ResponseDto(MessageType messageType, T messageObject) {
        this.messageType = messageType;
        this.messageObject = messageObject;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public T getMessageObject() {
        return messageObject;
    }

    public void setMessageObject(T messageObject) {
        this.messageObject = messageObject;
    }
}
