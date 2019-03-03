package lasermaze.service;

import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.domain.game.ChessSquare;
import lasermaze.dto.PieceLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
