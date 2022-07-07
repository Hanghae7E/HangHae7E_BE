package hanghae7e6.prototype.exception;

import org.springframework.http.HttpStatus;

public class AbstractException extends RuntimeException {
    private HttpStatus httpStatus;

    public AbstractException() {super("Abstract Exception 발생");};

    AbstractException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
