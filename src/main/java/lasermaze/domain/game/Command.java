package lasermaze.domain.game;

import lasermaze.domain.game.piece.Piece;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.domain.game.user.GameUser;
import lasermaze.domain.message.CommandMessage;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class Command {
    private static final Logger log = getLogger(Command.class);

    private Point point;
    private int commandNumber;

    public Command(Point point, int commandNumber) {
        this.point = point;
        this.commandNumber = commandNumber;
    }

    public List<List<CommandMessage>> execute(Board board, GameUser gameUser) {
        Piece piece = board.getPiece(point);

        int countOfDirection = Direction.values().length;
        if (commandNumber < countOfDirection) {
            move(board, piece);
        }

        if (commandNumber >= countOfDirection) {
            piece.rotate(Rotation.getRotation(commandNumber));
        }

        return board.shoot(gameUser);
    }

    private void move(Board board, Piece piece) {
        Direction direction = Direction.getDirection(commandNumber);
        if(hasBarrier(point, direction) || hasObstacle(board, direction)) {
            throw new NotSupportedException("Can not move Chess Piece");
        }
        piece.move(direction);
        board.swap(point, direction);
    }

    public static boolean hasBarrier(Point point, Direction direction) {
        return point.getNextPoint(direction).isOutOfBound();
    }

    public boolean hasObstacle(Board board, Direction direction) {
        return board.hasObstacle(point, direction);
    }


}
