package hanghae7e6.prototype.milestone;

//import hanghae7e6.prototype.common.BaseTimeEntity;
import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
@Table(name = "milestones")
public class MilestoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MILESTONE_ID", nullable = false)
    private Long milestoneId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "MILESTONE_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private MilestoneStatus milestoneStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private ProjectMemberEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_IN_CHARGE_ID")
    private ProjectMemberEntity personInCharge;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @Column(name = "COMPLETED_BY", nullable = false)
    private LocalDate completedBy;

    @Column(name = "BODY", nullable = false)
    private String body;


    @Builder
    public MilestoneEntity(String title, MilestoneStatus milestoneStatus,
                           ProjectMemberEntity author, ProjectMemberEntity personInCharge,
                           LocalDate createdAt, LocalDate completedBy, String body) {
        this.title = title;
        this.milestoneStatus = milestoneStatus;
        this.author = author;
        this.personInCharge = personInCharge;
        this.createdAt = createdAt;
        this.completedBy = completedBy;
        this.body = body;
    }

    public void updateMilestone(MilestoneRequest milestoneRequest, ProjectMemberEntity personInCharge) {
        this.title = milestoneRequest.getTitle();
        this.milestoneStatus = MilestoneStatus.valueOf(milestoneRequest.getMilestoneStatus());
        this.personInCharge = personInCharge;
        this.completedBy = LocalDate.parse(milestoneRequest.getCompletedBy(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.body = milestoneRequest.getBody();
    }
}

