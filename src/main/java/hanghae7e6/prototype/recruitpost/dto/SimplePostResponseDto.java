package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagDto;
import hanghae7e6.prototype.tag.TagResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplePostResponseDto {

    private Long postId;

    private String username;

    private String introduce;

    private String userPosition;

    private String authorImage;

    private String title;

    private String projectImage;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    private List<TagResponseDto> tags = new ArrayList<>();

    private List<TagResponseDto> authorFields;

    public static SimplePostResponseDto toDto(RecruitPostEntity entity) {
        String username = entity.getUser().getUsername();
        String userImg = entity.getProfile().getImageUrl();
        return SimplePostResponseDto.builder().postId(entity.getId())
                                    .username(username).authorImage(userImg)
                                    .title(entity.getTitle()).projectImage(entity.getImageUrl())
                                    .requiredDesigners(entity.getRequiredDesigners())
            .requiredDevelopers(entity.getRequiredDevelopers())
            .requiredProjectManagers(entity.getRequiredProjectManagers())
            .projectEndTime(entity.getProjectEndTime())
            .projectStartTime(entity.getProjectStartTime())
            .recruitDueTime(entity.getRecruitDueTime())
            .build();
    }

    public void addTag(Long tagId) {
    }
}
