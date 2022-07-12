package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "RECRUIT_POSTS")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostEntity extends BaseTimeEntity {

    @Id
    @Column(name = "RECRUIT_POST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true)
    private List<RecruitPostTagEntity> recruitPostTag;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private int totalMemberCount;

    @Column
    private LocalDate projectStartTime;

    @Column
    private LocalDate projectEndTime;

    @Column
    private LocalDate recruitDueTime;

    @Column
    private String imageUrl;


    public RecruitPostEntity updateFields(PostRequestDto requestDto){
        String title = requestDto.getTitle();
        String body = requestDto.getBody();
        Integer totalMemberCount = requestDto.getTotalMemberCount();
        LocalDate projectStartTime = requestDto.getProjectStartTime();
        LocalDate projectEndTime = requestDto.getProjectEndTime();
        LocalDate recruitDueTime = requestDto.getRecruitDueTime();

        this.title = Objects.nonNull(title)? title : this.title;
        this.body = Objects.nonNull(body)? body : this.body;

        this.totalMemberCount = Objects.nonNull(totalMemberCount)?
                totalMemberCount : this.totalMemberCount;

        this.projectStartTime = Objects.nonNull(projectStartTime)?
                projectStartTime : this.projectStartTime;

        this.projectEndTime = Objects.nonNull(projectEndTime)?
                projectEndTime : this.projectEndTime;

        this.recruitDueTime = Objects.nonNull(recruitDueTime)?
                recruitDueTime : this.recruitDueTime;


//        if (Objects.nonNull(img)) {
//            awsS3Service.deleteFile(this.imgUrl);
//            this.imgUrl = awsS3Service.uploadFile(img);
//        }

        return this;
    }
}
