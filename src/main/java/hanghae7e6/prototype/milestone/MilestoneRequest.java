package hanghae7e6.prototype.milestone;

import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class MilestoneRequest{
    private String title;
    private String milestoneStatus;
    private Long authorId;
    private Long personInChargeId;
    private String createdAt;
    private String completedBy;
    private String body;

    @Builder
    public MilestoneRequest(String title, String milestoneStatus, Long authorId, Long personInChargeId,
                            String createdAt, String completedBy, String body) {
        this.title = title;
        this.milestoneStatus = milestoneStatus;
        this.authorId = authorId;
        this.personInChargeId = personInChargeId;
        this.createdAt = createdAt;
        this.completedBy = completedBy;
        this.body = body;
    }


    public MilestoneEntity toEntity(ProjectMemberEntity author, ProjectMemberEntity personInCharge) {
        return MilestoneEntity.builder()
                .title(getTitle())
                .milestoneStatus(MilestoneStatus.valueOf(getMilestoneStatus()))
                .author(author)
                .personInCharge(personInCharge)
                .createdAt(LocalDate.parse(getCreatedAt(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .completedBy(LocalDate.parse(getCompletedBy(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .body(getBody())
                .build();
    }
}
