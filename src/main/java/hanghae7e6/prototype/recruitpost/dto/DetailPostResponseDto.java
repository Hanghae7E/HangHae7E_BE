package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagResponseDto;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private List<TagResponseDto> tags;

    private List<?> applicants;


    public DetailPostResponseDto(RecruitPostEntity post){


        this.userId = post.getUser().getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.projectStartTime = post.getProjectStartTime();
        this.projectEndTime = post.getProjectEndTime();
        this.recruitDueTime = post.getRecruitDueTime();
        this.totalHeadCount = post.getTotalMemderCount();
        this.tags = TagResponseDto.getDtos(post);
//        this.applicants = post.getApplicants
    }


}
