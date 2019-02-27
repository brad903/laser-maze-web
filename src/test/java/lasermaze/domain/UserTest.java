package lasermaze.domain;

import lasermaze.support.test.BaseTest;
import org.junit.Test;

public class UserTest extends BaseTest {

    @Test
    public void 패스워드일치_성공() {
        User user = new User("doby", "1234", "doby");
        softly.assertThat(user.matchPassword("1234")).isTrue();
    }

    @Test
    public void 패스워드불일치_실패() {
        User user = new User("doby", "1234", "doby");
        softly.assertThat(user.matchPassword("wrong")).isFalse();
    }
}
