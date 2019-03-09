package lasermaze.socket;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

public class MessageType {

    private String Path;
    private RequestMethod Method;

    public MessageType(String path, RequestMethod method) {
        Path = path;
        Method = method;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public RequestMethod getMethod() {
        return Method;
    }

    public void setMethod(RequestMethod method) {
        Method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageType that = (MessageType) o;
        return Objects.equals(Path, that.Path) &&
                Method == that.Method;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "Path='" + Path + '\'' +
                ", Method=" + Method +
                '}';
    }
}
