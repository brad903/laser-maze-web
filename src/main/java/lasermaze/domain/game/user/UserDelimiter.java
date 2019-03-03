package lasermaze.domain.game.user;

public enum UserDelimiter {
    BLACK("black"),
    WHITE("white"),
    DUMMY("dummy");

    String delimiter;

    UserDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
