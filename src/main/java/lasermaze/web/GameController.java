package lasermaze.web;


import lasermaze.SocketController;
import lasermaze.security.HttpSessionUtils;
import lasermaze.service.GameService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.WebSocketSession;

import static org.slf4j.LoggerFactory.getLogger;

@SocketController
public class GameController {
    private static final Logger log = getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public void joinRoom(WebSocketSession webSocketSession) {
        log.debug("user : {}", webSocketSession.getAttributes().get(HttpSessionUtils.USER_SESSION_KEY));
    }
}
