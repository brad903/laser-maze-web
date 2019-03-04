package lasermaze.socket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lasermaze.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class MessageSendUtils {

    private static final Logger log = LoggerFactory.getLogger(MessageSendUtils.class);

    public static void sendMessage(WebSocketSession session, ResponseDto responseDto) {
        try {
            session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(responseDto)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}