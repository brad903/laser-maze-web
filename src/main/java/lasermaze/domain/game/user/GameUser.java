package lasermaze.domain.game.user;

import java.util.Objects;

public class GameUser {
    public static final GameUser DUMMY_GAME_USER = new DummyGameUser();

    private String name;
    private boolean isWinner;


    public GameUser(String name) {
        this.name = name;
    }

    public boolean isDummyUser() {
        return false;
    }

    private static class DummyGameUser extends GameUser {
        private static final String DUMMY_NAME = "DUMMY";

        public DummyGameUser() {
            super(DUMMY_NAME);
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
        return Objects.equals(name, gameUser.name);
    }
}
