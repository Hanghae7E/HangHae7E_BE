package hanghae7e6.prototype.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorRes {
    private Integer status;
    private String message;
}
