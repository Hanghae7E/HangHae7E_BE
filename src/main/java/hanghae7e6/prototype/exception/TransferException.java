package hanghae7e6.prototype.exception;

import org.springframework.http.HttpStatus;

public class TransferException extends AbstractException {

    public TransferException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "서버 문제임. 엔티티 -> DTO 변환 시 나오는 에러. 확인 시 상훈이에게 꼭 알릴것!!");
    }
}
