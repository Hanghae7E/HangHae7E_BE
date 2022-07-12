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


    @Column(name = "required_developers", columnDefinition = "integer default 0")
    private Integer requiredDevelopers;

    @Column(name = "required_designers", columnDefinition = "integer default 0")
    private Integer requiredDesigners;

    @Column(name = "required_project_managers", columnDefinition = "integer default 0")
    private Integer requiredProjectManagers;

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
        LocalDate projectStartTime = requestDto.getProjectStartTime();
        LocalDate projectEndTime = requestDto.getProjectEndTime();
        LocalDate recruitDueTime = requestDto.getRecruitDueTime();

        this.title = Objects.nonNull(title)? title : this.title;
        this.body = Objects.nonNull(body)? body : this.body;

        this.projectStartTime = Objects.nonNull(projectStartTime)?
                projectStartTime : this.projectStartTime;

        this.projectEndTime = Objects.nonNull(projectEndTime)?
                projectEndTime : this.projectEndTime;

        this.recruitDueTime = Objects.nonNull(recruitDueTime)?
                recruitDueTime : this.recruitDueTime;


        this.requiredProjectManagers = requestDto.getRequiredProjectManagers();
        this.requiredDesigners = requestDto.getRequiredDesigners();
        this.requiredDevelopers = requestDto.getRequiredDevelopers();

//        if (Objects.nonNull(img)) {
//            awsS3Service.deleteFile(this.imgUrl);
//            this.imgUrl = awsS3Service.uploadFile(img);
//        }

        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
