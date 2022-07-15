package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.applicant.ApplicantEntity;
import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
@NamedEntityGraphs({
    @NamedEntityGraph(name = "RecruitPost.findAll",attributeNodes = {
        @NamedAttributeNode("user"),
        @NamedAttributeNode("profile"),
        @NamedAttributeNode("recruitPostTag")}),

@NamedEntityGraph(
    name = "RecruitPost.findAllByTagId", attributeNodes = {@NamedAttributeNode("user"), @NamedAttributeNode("profile"), @NamedAttributeNode(value = "recruitPostTag", subgraph = "recruitPostTag")},
    subgraphs = @NamedSubgraph(name = "recruitPostTag", attributeNodes = {@NamedAttributeNode("tag")}))
})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID")
    private ProfileEntity profile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true)
    private List<RecruitPostTagEntity> recruitPostTag = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true)
    private List<ApplicantEntity> applicants = new ArrayList<>();


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

    @Column
    private Boolean recruitStatus;

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


        this.requiredProjectManagers = Optional.ofNullable(requestDto.getRequiredProjectManagers()).orElseGet(() -> this.requiredProjectManagers);
        this.requiredDesigners =  Optional.ofNullable(requestDto.getRequiredDesigners()).orElseGet(() -> this.requiredDesigners);
        this.requiredDevelopers =  Optional.ofNullable(requestDto.getRequiredDevelopers()).orElseGet(() -> this.requiredDevelopers);

        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addApplicant(ApplicantEntity applicant) {
        this.applicants.add(applicant);

        if (applicant.getRecruitPost() != this) {
            applicant.setRecruitPost(this);
        }
    }

    public List<ApplicantEntity> getAcceptedApplicantByPosition(String positionName) {
        return this.applicants.stream()
                              .filter(applicant -> applicant.getPosition().equals(positionName) && applicant.getStatus().equals("합격"))
                              .collect(Collectors.toList());
    }

}
