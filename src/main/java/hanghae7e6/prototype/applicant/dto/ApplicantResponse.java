package hanghae7e6.prototype.applicant.dto;

import hanghae7e6.prototype.applicant.ApplicantEntity;
import hanghae7e6.prototype.exception.TransferException;
import hanghae7e6.prototype.user.UserEntity;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicantResponse {

//    private Long profileId;
//    private String profileImageUrl;
//    private String email;
//    private String username;
//    private String position;
//    private String status;



//    public ApplicantResponse toResponse(ApplicantEntity entity) throws TransferException {
//        UserEntity user = Optional.ofNullable(entity.getUser()).orElseThrow(TransferException::new);
//
//        return ApplicantResponse.builder().userId(user.getId()).profileImageUrl(user.get)
//    }
}
