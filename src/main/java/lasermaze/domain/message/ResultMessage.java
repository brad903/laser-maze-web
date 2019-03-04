package lasermaze.domain.message;

import lasermaze.domain.game.Command;
import lasermaze.domain.game.GameResult;

import java.util.List;

public class ResultMessage {

    private static final MessageType MESSAGE_TYPE = MessageType.RESULT;
    private CommandMessage commandMessage;
    private List<List<CommandMessage>> laserMovements;
    private GameResult gameResult;

    public ResultMessage() {

    }

    public ResultMessage(CommandMessage commandMessage, List<List<CommandMessage>> laserMovements, GameResult gameResult) {
        this.commandMessage = commandMessage;
        this.laserMovements = laserMovements;
        this.gameResult = gameResult;
    }

    public void setCommandMessage(CommandMessage commandMessage) {
        this.commandMessage = commandMessage;
    }

    public List<List<CommandMessage>> getLaserMovements() {
        return laserMovements;
    }

    public void setLaserMovements(List<List<CommandMessage>> laserMovements) {
        this.laserMovements = laserMovements;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}
