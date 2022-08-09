package hanghae7e6.prototype.milestone;

import lombok.Getter;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
public class MilestoneResponse {
    private String title;
    private String milestoneStatus;
    private Long authorId;
    private Long personInChargeId;
    private String dueDay;
    private String body;


    public MilestoneResponse(MilestoneEntity milestone) {
        this.title = milestone.getTitle();
        this.milestoneStatus = milestone.getMilestoneStatus().toString();
        this.authorId = milestone.getAuthor().getId();
        this.personInChargeId = milestone.getPersonInCharge().getId();
        this.body = milestone.getBody();
        this.dueDay = "D-" + DAYS.between(milestone.getCreatedAt(), LocalDate.now());
    }
}
