package lasermaze.domain.game;

import lasermaze.domain.game.piece.King;
import lasermaze.domain.game.piece.Piece;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.user.GameUser;

import static lasermaze.domain.game.ChessSquare.CHESSBOARD_SIZE;

public class Game {

    private Board board;
    private GameUser gameUser1;
    private GameUser gameUser2;

    public Game(GameUser gameUser1, GameUser gameUser2) {
        this.gameUser1 = gameUser1;
        this.gameUser2 = gameUser2;
        board = new Board(new ChessSquare(gameUser1, gameUser2).pieceInit());
    }

    public void start() {
        int turn = 0;
        GameResult result = GameResult.NOT_DECIDED;
        while (result == GameResult.NOT_DECIDED) {
            int[] input = {4, 0, 3};

            Command command = new Command(new Point(input[0], input[1]), input[2]);
            command.execute(board, getCurrentPlayer(turn));
            turn++;

            result = getResult(this.board);
        }
    }

    public GameResult getResult(Board board) {
        boolean user1King = hasKing(gameUser1, board);
        boolean user2King = hasKing(gameUser2, board);

        if(user1King && user2King) return GameResult.NOT_DECIDED;
        if(user1King) return GameResult.USER1;
        if(user2King) return GameResult.USER2;
        return GameResult.DRAW;
    }

    public static boolean hasKing(GameUser gameUser, Board board) {
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            for (int j = 0; j < CHESSBOARD_SIZE; j++) {
                Piece piece = board.getPiece(new Point(i, j));
                if(piece instanceof King && piece.isSameUser(gameUser)) {
                    return true;
                }
            }
        }

        return false;
    }

    public GameUser getCurrentPlayer(int turn) {
        if (turn % 2 == 0) return gameUser1;
        return gameUser2;
    }


}

