package lasermaze.security;

import lasermaze.UnAuthenticationException;
import lasermaze.UnSupportedFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestSecurityControllerAdvice {

    @ExceptionHandler(UnSupportedFormatException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void unSupportedFormatException() {

    }


}
