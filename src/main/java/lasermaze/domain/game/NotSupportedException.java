package lasermaze.domain.game;

public class NotSupportedException extends RuntimeException {

    public NotSupportedException() {
    }

    public NotSupportedException(String message) {
        super(message);
    }

}
