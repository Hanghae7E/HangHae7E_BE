package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.applicant.ApplicantEntity;
import hanghae7e6.prototype.applicant.dto.ApplicantResponse;
import hanghae7e6.prototype.exception.TransferException;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagResponseDto;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailPostResponseDto {

    private Long userId;

    private Long postId;

    private String title;

    private String body;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private String imageUrl;

    private List<TagResponseDto> tags;

    private List<ApplicantResponse> applicants;

    public static DetailPostResponseDto toDto(RecruitPostEntity entity, List<TagEntity> tags) throws TransferException {
        if (entity.getUser() == null) throw new TransferException();

        return DetailPostResponseDto.builder().postId(entity.getId())
                                    .userId(entity.getUser().getId())
                                    .title(entity.getTitle())
                                    .body(entity.getBody())
                                    .imageUrl(entity.getImageUrl())
                                    .requiredDesigners(entity.getRequiredDesigners())
                                    .requiredDevelopers(entity.getRequiredDevelopers())
                                    .requiredProjectManagers(entity.getRequiredProjectManagers())
                                    .projectEndTime(entity.getProjectEndTime())
                                    .projectStartTime(entity.getProjectStartTime())
                                    .recruitDueTime(entity.getRecruitDueTime())
                                    .tags(TagResponseDto.toDtos(tags))
                                    .build();
    }


    public static DetailPostResponseDto toDto(RecruitPostEntity entity, List<TagEntity> tags, List<ApplicantEntity> applicants) throws TransferException {
        if (entity.getUser() == null) throw new TransferException();

        return DetailPostResponseDto.builder().postId(entity.getId())
                                    .userId(entity.getUser().getId())
                                    .title(entity.getTitle())
                                    .body(entity.getBody())
                                    .imageUrl(entity.getImageUrl())
                                    .requiredDesigners(entity.getRequiredDesigners())
                                    .requiredDevelopers(entity.getRequiredDevelopers())
                                    .requiredProjectManagers(entity.getRequiredProjectManagers())
                                    .projectEndTime(entity.getProjectEndTime())
                                    .projectStartTime(entity.getProjectStartTime())
                                    .recruitDueTime(entity.getRecruitDueTime())
                                    .tags(TagResponseDto.toDtos(tags))
                                    .applicants(ApplicantResponse.toResponses(applicants))
                                    .build();
    }
}
