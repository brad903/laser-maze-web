package lasermaze.socket;

import lasermaze.dto.MessageDto;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

public class MessageType {

    private String path;
    private RequestMethod requestMethod;

    public MessageType() {
    }

    public MessageType(String path, RequestMethod requestMethod) {
        this.path = path;
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public MessageDto _toMessageDto(WebSocketSession session) {
        return new MessageDto(this, session);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageType that = (MessageType) o;
        return Objects.equals(path, that.path) &&
                requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, requestMethod);
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "path='" + path + '\'' +
                ", requestMethod=" + requestMethod +
                '}';
    }
}
