package hanghae7e6.prototype.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class InvalidException extends AbstractException{
    private final ErrorCode error;

    public InvalidException(ErrorCode error) {
        super(BAD_REQUEST, error.getMessage());
        this.error = error;
    }
}
