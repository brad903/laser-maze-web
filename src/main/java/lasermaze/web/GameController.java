package lasermaze.web;


import lasermaze.SocketController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SocketController
public class GameController {

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public void joinRoom() {

    }
}
