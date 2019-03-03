package lasermaze.web;

import lasermaze.domain.GameRoom;
import lasermaze.domain.User;
import lasermaze.security.LoginUser;
import lasermaze.service.GameService;
import lasermaze.socket.WebSocketSessionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/games")
public class GameRoomController {
    private static final Logger log = getLogger(GameRoomController.class);

    @Autowired
    private GameService gameService;

    @PostMapping
    public String create(@LoginUser User user) {
        GameRoom gameRoom = GameRoom.create(user.getName());
        gameService.create(gameRoom);
        return "redirect:/games/join/" + gameRoom.getId();
    }

    @GetMapping("/join/{id}")
    public String join(@LoginUser User user, @PathVariable String id, HttpSession httpSession, Model model) {
        GameRoom gameRoom = gameService.getGameRoom(id);
        if (gameRoom.isFull()) return "redirect:/";
        httpSession.setAttribute(WebSocketSessionUtils.GAME_SESSION_KEY, id);
        model.addAttribute("chessSquares", gameService.getPieceLineDtos());
        return "gameRoom";
    }

}
