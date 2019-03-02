package lasermaze.socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class MessageSendUtils {

    private static final Logger log = LoggerFactory.getLogger(MessageSendUtils.class);

    public static void sendMessage(WebSocketSession session, TextMessage message) {
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}