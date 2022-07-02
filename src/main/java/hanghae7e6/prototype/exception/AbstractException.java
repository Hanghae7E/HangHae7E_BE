package hanghae7e6.prototype.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {
    private final HttpStatus httpStatus;

    AbstractException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
