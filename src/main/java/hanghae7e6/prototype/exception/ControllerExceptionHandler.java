package hanghae7e6.prototype.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidException.class)
    ResponseEntity<ErrorRes> handleInvalidException(InvalidException e) {
        ErrorRes errorRes = ErrorRes.builder().status(400).message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
    }


    @ExceptionHandler(UnAuthorizedException.class)
    ResponseEntity<ErrorRes> handleUnAuthorizedException(UnAuthorizedException e) {
        ErrorRes errorRes = ErrorRes.builder().status(401).message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorRes);
    }


    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorRes> handleNotFoundException(NotFoundException e) {
        ErrorRes errorRes = ErrorRes.builder().status(404).message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){

        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .peek(System.out::println)
                .findFirst()
                .orElseGet(() -> "there's no error message");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
