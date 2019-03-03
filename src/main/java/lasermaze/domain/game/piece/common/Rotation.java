package lasermaze.domain.game.piece.common;

public enum Rotation {
    CLOCKWISE(9), COUNTERCLOCKWISE(10);

    private int commandNumber;

    Rotation(int commandNumber) {
        this.commandNumber = commandNumber;
    }

    public static Rotation getRotation(int commandNumber) {
        for (Rotation rotation : values()) {
            if(rotation.commandNumber == commandNumber) {
                return rotation;
            }
        }
        return null;
    }
}
