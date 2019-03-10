package lasermaze.socket;

import lasermaze.dto.MessageDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class ParameterBinder {
    private static final Logger log = getLogger(ParameterBinder.class);

    private static final List<HandlerMethodArgumentResolver> methodArgumentResolvers = new ArrayList<>();

    static {
        methodArgumentResolvers.add(new UserHandlerMethodArgumentResolver());
    }

    public Object[] bind(Method method, MessageDto messageDto) throws Exception {
        Object[] args = new Object[method.getParameterCount()];
        Class<?>[] types = method.getParameterTypes();
        for (int i = 0; i < args.length; i++) {
            args[i] = types[i].newInstance();
            log.debug(types[i].getName());
            for (HandlerMethodArgumentResolver methodArgumentResolver : methodArgumentResolvers) {
                if (methodArgumentResolver.supportsParameter(types[i])) {
                    args[i] = methodArgumentResolver.resolveArgument(messageDto);
                }
            }
        }
        return args;
    }
}
