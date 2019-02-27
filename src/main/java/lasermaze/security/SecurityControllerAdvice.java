package lasermaze.security;

import lasermaze.UnAuthenticationException;
import lasermaze.UnSupportedFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = Controller.class)
public class SecurityControllerAdvice {

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String unAuthenticationException() {
        return "user/login";
    }

    @ExceptionHandler(UnSupportedFormatException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String unSupportedFormatException() {
        return "user/join";
    }


}
