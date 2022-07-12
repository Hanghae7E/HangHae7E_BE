package hanghae7e6.prototype.applicant;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "APPLICANTS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity {
    @Id
    @Column(name = "APPLICANT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUIT_POST_ID")
    private RecruitPostEntity recruitPost;

    @Column(nullable = false)
    private Integer status;
}
