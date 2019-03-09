package lasermaze.web;


import lasermaze.SocketController;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

@SocketController
public class GameController {
    private static final Logger log = getLogger(GameController.class);

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public void joinRoom() {
        log.debug("invoke 성공!");
    }
}
