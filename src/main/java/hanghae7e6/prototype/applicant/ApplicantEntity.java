package hanghae7e6.prototype.applicant;

import hanghae7e6.prototype.applicant.dto.ApplicantRequest;
import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.util.Optional;
import lombok.*;

import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Builder
@Table(name = "APPLICANTS")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity extends BaseTimeEntity {
    @Id
    @Column(name = "APPLICANT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUIT_POST_ID")
    private RecruitPostEntity recruitPost;

    @Column(name = "POSITION")
    private String position;

    @Column(columnDefinition = "varchar(10) default '대기중'")
    private String status;

    public void setRecruitPost(RecruitPostEntity recruitPost) {
        this.recruitPost = recruitPost;
    }

    public void update(ApplicantRequest applicantRequest) {
        this.status = Optional.ofNullable( applicantRequest.getStatus()).orElseGet(this::getStatus);
        this.position = Optional.ofNullable(applicantRequest.getPosition()).orElseGet(this::getPosition);
    }
}
