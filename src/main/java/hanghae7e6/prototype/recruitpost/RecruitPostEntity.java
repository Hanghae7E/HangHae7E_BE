package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
