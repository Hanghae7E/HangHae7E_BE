package hanghae7e6.prototype.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends AbstractException {
    private final ErrorCode error;

    public NotFoundException(ErrorCode error) {
        super(NOT_FOUND, error.getMessage());
        this.error = error;
    }
}
