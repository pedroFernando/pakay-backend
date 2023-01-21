package ec.com.pakay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PakayPreconditionFailedException extends RuntimeException {
    public PakayPreconditionFailedException(String message) {
        super(message);
    }
}
