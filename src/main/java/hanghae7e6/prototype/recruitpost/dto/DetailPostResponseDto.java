package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailPostResponseDto {

    private Long userId;

    private String title;

    private String body;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    private int totalHeadCount;

    private List<?> tagList;

    private List<?> applicants;


    public DetailPostResponseDto(RecruitPostEntity post){
        this.userId = post.getUser().getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.projectStartTime = post.getProjectStartTime();
        this.projectEndTime = post.getProjectEndTime();
        this.recruitDueTime = post.getRecruitDueTime();
        this.totalHeadCount = post.getTotalMemderCount();
//        this.tagList = post.getTag
//        this.applicants = post.getApplicants
    }
}
