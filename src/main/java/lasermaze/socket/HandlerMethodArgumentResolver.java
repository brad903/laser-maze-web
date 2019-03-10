package lasermaze.socket;

import lasermaze.dto.MessageDto;

public interface HandlerMethodArgumentResolver {

    boolean supportsParameter(Class<?> parameterType);

    Object resolveArgument(MessageDto messageDto);
}
