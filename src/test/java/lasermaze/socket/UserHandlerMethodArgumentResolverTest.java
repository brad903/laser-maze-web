package lasermaze.socket;

import lasermaze.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserHandlerMethodArgumentResolverTest {

    @Test
    public void isSameClass() {
        Class<?> parameterType = User.class;
        assertThat(parameterType.equals(User.class)).isTrue();
    }
}