package lasermaze.socket;

import lasermaze.SocketController;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class GameControllerMappingHandler {
    public static final String ROOT_PACKAGE = "lasermaze";

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
        for (Method method : methods) {
            if (!method.isAnnotationPresent(RequestMapping.class)) continue;
            MessageType messageType = _toMessageType(method);
            mapper.put(messageType, method);
        }
    }

    private static MessageType _toMessageType(Method method) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        return new MessageType(requestMapping.value()[0], requestMapping.method()[0]);
    }
}
