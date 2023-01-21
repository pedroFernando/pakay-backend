package ec.com.pakay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PakayConflictException extends RuntimeException {
    public PakayConflictException(String message) {
        super(message);
    }
}
