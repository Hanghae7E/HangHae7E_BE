package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostEntity {

    @Id
    @Column(name = "RECRUIT_POST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recruitPost", orphanRemoval = true)
    private List<RecruitPostTagEntity> recruitPostTag;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private int totalMemderCount;

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
        Integer totalMemderCount = requestDto.getTotalMemberCount();
        String projectStartTime = requestDto.getProjectStartTime();
        String projectEndTime = requestDto.getProjectEndTime();
        String recruitDueTime = requestDto.getRecruitDueTime();

        this.title = Objects.nonNull(title)? title : this.title;
        this.body = Objects.nonNull(body)? body : this.body;

        this.totalMemderCount = Objects.nonNull(totalMemderCount)?
                totalMemderCount : this.totalMemderCount;

        this.projectStartTime = Objects.nonNull(projectStartTime)?
                LocalDate.parse(projectStartTime) : this.projectStartTime;

        this.projectEndTime = Objects.nonNull(projectEndTime)?
                LocalDate.parse(projectEndTime) : this.projectEndTime;

        this.recruitDueTime = Objects.nonNull(recruitDueTime)?
                LocalDate.parse(recruitDueTime) : this.recruitDueTime;


//        if (Objects.nonNull(img)) {
//            awsS3Service.deleteFile(this.imgUrl);
//            this.imgUrl = awsS3Service.uploadFile(img);
//        }

        return this;
    }
}
