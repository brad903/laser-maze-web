package lasermaze.web;


import lasermaze.SocketController;
import lasermaze.domain.User;
import lasermaze.service.GameService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.WebSocketSession;

import static org.slf4j.LoggerFactory.getLogger;

@SocketController
@Component
public class GameController {
    private static final Logger log = getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public void joinRoom(User user, WebSocketSession webSocketSession) {
        log.debug("호출");
        log.debug("gameService : {}", gameService);
        gameService.joinRoom(user, webSocketSession);
    }
}
