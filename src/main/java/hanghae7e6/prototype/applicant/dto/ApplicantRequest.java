package hanghae7e6.prototype.applicant.dto;


import hanghae7e6.prototype.applicant.ApplicantEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantRequest {

    private Long userId;
    private Long postId;
    private String position;
    private String status;

    public ApplicantEntity toEntity(UserEntity user, RecruitPostEntity recruitPost) {
        return ApplicantEntity.builder().recruitPost(recruitPost).user(user).position(this.position).status(this.status).build();
    }
}
