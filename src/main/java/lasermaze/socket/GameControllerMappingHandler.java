package lasermaze.socket;

import lasermaze.SocketController;
import lasermaze.dto.MessageDto;
import lasermaze.web.GameController;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class GameControllerMappingHandler {
    private static final Logger log = getLogger(GameControllerMappingHandler.class);

    @Autowired
    private ParameterBinder parameterBinder;

    private final Map<MessageType, Pair> mapper = new HashMap<>();

    @Autowired
    public GameControllerMappingHandler(ApplicationContext context) {
        init(context);
    }

    private void init(ApplicationContext context) {
        Map<String, Object> controllers = context.getBeansWithAnnotation(SocketController.class);
        for (String key : controllers.keySet()) {
            Object object = controllers.get(key);
            Class<?> clazz = object.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            registerMethods(object, methods);
        }
    }

    private void registerMethods(Object object, Method[] methods) {
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                .forEach(method -> mapper.put(_toMessageType(method), new Pair(object, method)));
    }

    private MessageType _toMessageType(Method method) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        return new MessageType(requestMapping.value()[0], requestMapping.method()[0]);
    }

    public void invoke(MessageDto messageDto) throws Exception {
        Pair pair = mapper.get(messageDto.getMessageType());
        Object[] args = parameterBinder.bind(pair.method, messageDto);
        pair.method.invoke(pair.object, args);
    }

    static class Pair {
        private Object object;
        private Method method;

        public Pair(Object object, Method method) {
            this.object = object;
            this.method = method;
        }
    }
}
