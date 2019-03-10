package lasermaze.socket;

import lasermaze.SocketController;
import lasermaze.dto.MessageDto;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    public static final String ROOT_PACKAGE = "lasermaze";

    @Autowired
    private ParameterBinder parameterBinder;

    private static final Map<MessageType, Method> mapper = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(SocketController.class);

        for (Class<?> controller : controllers) {
            Method[] methods = controller.getMethods();
            registerMethods(methods);
        }
    }

    private static void registerMethods(Method[] methods) {
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                .forEach(method -> mapper.put(_toMessageType(method), method));
    }

    private static MessageType _toMessageType(Method method) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        return new MessageType(requestMapping.value()[0], requestMapping.method()[0]);
    }

    public void invoke(MessageDto messageDto) throws Exception {
        Method method = mapper.get(messageDto.getMessageType());
        Object[] args = parameterBinder.bind(method, messageDto);
        Object clazz = method.getDeclaringClass().newInstance();
        method.invoke(clazz, args);
    }
}
