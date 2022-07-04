package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagService;
import hanghae7e6.prototype.user.UserEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @NotEmpty
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")
    private String projectStartTime;

    @NotEmpty
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")
    private String projectEndTime;

    @NotEmpty
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")
    private String recruitDueTime;

    @Min(1)
    private Integer totalMemberCount;

    private List<Long> tags;

    private MultipartFile img;

    public RecruitPostEntity getPostEntity(UserEntity user, RecruitPostTagService recruitPostTagService){

        RecruitPostEntity postEntity = RecruitPostEntity.builder()
                .user(user)
                .title(title)
                .body(body)
                .projectStartTime(Objects.nonNull(projectStartTime)?
                        LocalDate.parse(projectStartTime) : null)
                .projectEndTime(Objects.nonNull(projectEndTime)?
                        LocalDate.parse(projectEndTime) : null)
                .recruitDueTime(Objects.nonNull(recruitDueTime)?
                        LocalDate.parse(recruitDueTime) : null)
                .totalMemderCount(totalMemberCount)
//                .img(awsS3.save(img))
                .build();

        recruitPostTagService.saveTags(postEntity, tags);

        return postEntity;
    }
}
