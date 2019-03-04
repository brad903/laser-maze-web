package lasermaze.domain;

import lasermaze.domain.game.Game;
import lasermaze.domain.game.user.GameUser;
import lasermaze.domain.game.user.UserDelimiter;
import lasermaze.domain.message.CommandMessage;
import lasermaze.domain.message.MessageType;
import lasermaze.domain.message.ResultMessage;
import lasermaze.dto.ResponseDto;
import lasermaze.socket.MessageSendUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GameRoom {
    private static final Logger log = LoggerFactory.getLogger(GameRoom.class);

    public static final int MAX_PLAYER_COUNT = 2;
    private String id;
    private String name;
    private List<Player> players = new ArrayList<>();
    private Game game;

    public static GameRoom create(String name) {
        GameRoom created = new GameRoom();
        created.id = UUID.randomUUID().toString();
        created.name = name;
        return created;
    }

    public void start() {
        this.game = new Game(new GameUser(UserDelimiter.BLACK, players.get(0).getUserId())
                , new GameUser(UserDelimiter.WHITE, players.get(1).getUserId()));
    }

    public ResultMessage execute(CommandMessage commandMessage, User user) {
        return game.execute(commandMessage, user);
    }

    public void join(Player player) {
        players.add(player);
    }

    public void sendPlayerList() {
        send(new ResponseDto(MessageType.READY, players));
    }

    public <T> void send(ResponseDto<T> responseDto) {
        players.stream().forEach(player -> MessageSendUtils.sendMessage(player.getWebSocketSession(), responseDto));
    }

    public void remove(WebSocketSession target) {
        Optional<Player> removablePlayer = players.parallelStream()
                .filter(player -> player.hasSameSession(target))
                .findFirst();

        if (removablePlayer.isPresent()) {
            players.remove(removablePlayer.get());
            if (game != null) {
                MessageSendUtils.sendMessage(players.get(0).getWebSocketSession(), new ResponseDto(MessageType.DISCONNECTED, null));
                players.clear();
            }
            if (!players.isEmpty()) sendPlayerList();
        }
    }

    public Player getPlayer(User user) {
        return players.parallelStream().filter(player -> player.isSameUser(user)).findFirst().get();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public boolean isFull() {
        return players.size() == MAX_PLAYER_COUNT;
    }

    public boolean isAllReady() {
        return players.parallelStream().filter(player -> player.isReady()).count() == MAX_PLAYER_COUNT;
    }
}