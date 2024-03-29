package ec.com.pakay.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PakayInvalidTokenRequestException extends RuntimeException {

    private final String tokenType;
    private final String token;
    private final String message;
    private final String error;

    public PakayInvalidTokenRequestException(String tokenType, String token, String message) {
        super(String.format("%s: [%s] token: [%s] ", message, tokenType, token));
        this.tokenType = tokenType;
        this.token = token;
        this.message = message;
        this.error = message;
    }

}
