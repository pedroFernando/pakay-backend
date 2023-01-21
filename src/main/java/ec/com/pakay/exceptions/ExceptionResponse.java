package ec.com.pakay.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponse(Date timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
