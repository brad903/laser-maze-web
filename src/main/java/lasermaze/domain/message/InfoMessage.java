package lasermaze.domain.message;

public class InfoMessage {
    private String errrorMessage;

    public InfoMessage(String errrorMessage) {
        this.errrorMessage = errrorMessage;
    }

    public String getErrrorMessage() {
        return errrorMessage;
    }

    public void setErrrorMessage(String errrorMessage) {
        this.errrorMessage = errrorMessage;
    }
}
