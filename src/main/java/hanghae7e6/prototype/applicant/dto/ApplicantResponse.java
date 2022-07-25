package hanghae7e6.prototype.applicant.dto;

import hanghae7e6.prototype.applicant.ApplicantEntity;
import hanghae7e6.prototype.exception.TransferException;
import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicantResponse {

    private Long userId;
    private String profileImageUrl;
    private String email;
    private String username;
    private String position;
    private String status;



    public static ApplicantResponse toResponse(ApplicantEntity entity) throws TransferException {
        UserEntity user = Optional.ofNullable(entity.getUser()).orElseThrow(TransferException::new);
        ProfileEntity profile = Optional.ofNullable(user.getProfile()).orElseThrow(TransferException::new);
        String position = Optional.ofNullable(profile.getPosition()).orElseThrow(TransferException::new);

        return ApplicantResponse.builder().userId(user.getId())
                                .profileImageUrl(profile.getImageUrl())
                                .email(user.getEmail())
                                .username(user.getUsername())
                                .position(position)
                                .status(entity.getStatus())
                                .build();
    }

    public static List<ApplicantResponse> toResponses(List <ApplicantEntity> entities) throws TransferException {
        return entities.stream().map(ApplicantResponse::toResponse).collect(Collectors.toList());
    }
}
