package lasermaze.stomp;

import lasermaze.domain.CommandMessage;
import lasermaze.domain.JoinMessage;
import lasermaze.domain.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class CommandMessageController {
    private static final Logger log = getLogger(CommandMessageController.class);

    private final SimpMessagingTemplate template;

    @Autowired
    public CommandMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/games/join")
    public void join(JoinMessage joinMessage) {
        log.debug("join : {}", joinMessage.toString());
        template.convertAndSend("/subscribe/games/rooms/" + joinMessage.getRoomId(), joinMessage.getUser());
    }

    @MessageMapping("/games/message")
    public void message(CommandMessage message) {
        log.debug("message : {}", message);
        log.debug("/games/message 호출");
        template.convertAndSend("/subscribe/games/room/" + message.getId(), message);
    }
}
