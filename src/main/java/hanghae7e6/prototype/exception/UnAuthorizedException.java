package hanghae7e6.prototype.exception;


import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnAuthorizedException extends AbstractException {
    private final ErrorCode error;

    public UnAuthorizedException(ErrorCode error) {
        super(UNAUTHORIZED, error.getMessage());
        this.error = error;
    }


}
