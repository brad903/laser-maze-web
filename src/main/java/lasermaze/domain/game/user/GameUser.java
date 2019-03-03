package lasermaze.domain.game.user;

import java.util.Objects;

public class GameUser {
    public static final GameUser DUMMY_GAME_USER = new DummyGameUser();

    private UserDelimiter userDelimiter;
    private boolean isWinner;

    public GameUser(UserDelimiter userDelimiter) {
        this.userDelimiter = userDelimiter;
    }

    public boolean isDummyUser() {
        return false;
    }

    public boolean isSameDelimiter(UserDelimiter userDelimiter) {
        return this.userDelimiter.equals(userDelimiter);
    }

    private static class DummyGameUser extends GameUser {
        public DummyGameUser() {
            super(UserDelimiter.DUMMY);
        }

        @Override
        public boolean isDummyUser() {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameUser gameUser = (GameUser) o;
        return isWinner == gameUser.isWinner &&
                userDelimiter == gameUser.userDelimiter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDelimiter, isWinner);
    }
}
