package rest.exception;

import dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Exception handler. It catches build-in java exception thrown in rest services and provides ErrorDTO.
 *
 */
@ControllerAdvice
public class ExpectionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public String handleNoSuchElementException(Exception ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorDTO handleIllegalArgumentException(Exception ex) {
        return new ErrorDTO() //
                .setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase()) //
                .setStatusCode(HttpStatus.BAD_REQUEST.value()) //
                .setMessage(ex.getMessage());
    }

}
