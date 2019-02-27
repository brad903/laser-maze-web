package lasermaze.web;

import lasermaze.support.test.BasicAuthAcceptanceTest;
import lasermaze.support.test.HtmlFormDataBuilder;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserAcceptanceTest extends BasicAuthAcceptanceTest {

    @Test
    public void 회원가입_화면() {
        ResponseEntity<String> response = template.getForEntity("/users/join", String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 회원가입_정상() {
        HttpEntity httpEntity = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "dobyisfree")
                .addParameter("password", "1234")
                .addParameter("name", "dobyisfree")
                .build();
        ResponseEntity<String> response = template.postForEntity("/users/join", httpEntity, String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    public void 회원가입_아이디중복_실패() {
        HttpEntity httpEntity = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "doby")
                .addParameter("password", "1234")
                .addParameter("name", "dobyisfree")
                .build();
        ResponseEntity<String> response = template.postForEntity("/users/join", httpEntity, String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 회원가입_글자수3자리미만_실패() {
        HttpEntity httpEntity = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "by")
                .addParameter("password", "1234")
                .addParameter("name", "dobyisfree")
                .build();
        ResponseEntity<String> response = template.postForEntity("/users/join", httpEntity, String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 로그인_화면() {
        ResponseEntity<String> response = template.getForEntity("/users/login", String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_성공() {
        HttpEntity httpEntity = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "doby")
                .addParameter("password", "1234")
                .addParameter("name", "doby")
                .build();
        ResponseEntity<String> response = template.postForEntity("/users/login", httpEntity, String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    public void 로그인_등록되지않는아이디_실패() {
        HttpEntity httpEntity = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "doby123")
                .addParameter("password", "1234")
                .addParameter("name", "doby")
                .build();
        ResponseEntity<String> response = template.postForEntity("/users/login", httpEntity, String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 로그인_패스워드불일치_실패() {
        HttpEntity httpEntity = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "doby")
                .addParameter("password", "11111234")
                .addParameter("name", "doby")
                .build();
        ResponseEntity<String> response = template.postForEntity("/users/login", httpEntity, String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    @Test
    public void 로그아웃_성공() {
        ResponseEntity<String> response = template.withBasicAuth("doby", "1234")
                                            .getForEntity("/users/logout", String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }
}