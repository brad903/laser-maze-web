package lasermaze;

public class UnAuthenticationException extends RuntimeException {

    public UnAuthenticationException() {
        super();
    }

    public UnAuthenticationException(String message) {
        super(message);
    }
}
