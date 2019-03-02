package lasermaze.web;

import lasermaze.domain.GameRoomRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class HomeController {
    private static final Logger log = getLogger(HomeController.class);

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gameRooms", gameRoomRepository.getAllRooms());
        return "index";
    }
}
