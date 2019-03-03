package lasermaze.domain;

public class Message {
    private MessageType messageType;
    private CommandMessage message;

    public Message() {
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public CommandMessage getMessage() {
        return message;
    }

    public void setMessage(CommandMessage message) {
        this.message = message;
    }
}
