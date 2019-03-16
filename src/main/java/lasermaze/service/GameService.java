package lasermaze.service;

import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.domain.Player;
import lasermaze.domain.User;
import lasermaze.domain.game.ChessSquare;
import lasermaze.dto.PieceLineDto;
import lasermaze.socket.WebSocketSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRoomRepository gameRoomRepository;

    public void create(GameRoom gameRoom) {
        gameRoomRepository.save(gameRoom);
    }

    public GameRoom getGameRoom(String id) {
        return gameRoomRepository.getGameRoom(id);
    }

    public List<PieceLineDto> getPieceLineDtos() {
        return new ChessSquare().pieceInit()._toDto();
    }

    public void joinRoom(User user, WebSocketSession webSocketSession) {
        GameRoom gameRoom = getGameRoom(WebSocketSessionUtils.getGameRoomIdFromSocket(webSocketSession));
        gameRoom.join(Player.createPlayer(user, webSocketSession));
        gameRoom.sendPlayerList();
    }
}
