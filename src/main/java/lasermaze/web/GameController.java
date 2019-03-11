package lasermaze.web;


import lasermaze.SocketController;
import lasermaze.domain.GameRoom;
import lasermaze.domain.User;
import lasermaze.service.GameService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

@SocketController
public class GameController {
    private static final Logger log = getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public void joinRoom(GameRoom gameRoom, User user) {
        log.debug("invoke 성공!");
        log.debug("user : {}", user);
        log.debug("gameRoom : {}", gameRoom);
    }
}
