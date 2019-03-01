package lasermaze.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.domain.GameRoom;
import lasermaze.domain.GameRoomRepository;
import lasermaze.domain.User;
import lasermaze.security.LoginUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/games")
public class GameRoomController {
    private static final Logger log = getLogger(GameRoomController.class);

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @PostMapping
    public String create(@LoginUser User user) {
        GameRoom gameRoom = GameRoom.create(user.getName());
        gameRoomRepository.save(gameRoom);
        return "redirect:/games/join/" + gameRoom.getId();
    }

    @GetMapping("/join/{id}")
    public String join(@LoginUser User user, @PathVariable String id, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        GameRoom gameRoom = gameRoomRepository.getGameRoom(id);
        model.addAttribute("room", gameRoom);
        model.addAttribute("member", mapper.writeValueAsString(user));
        return "gameRoom";
    }

}
