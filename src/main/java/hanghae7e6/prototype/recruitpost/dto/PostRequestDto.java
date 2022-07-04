package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.domain.entity.UserEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private List<String> tagList;

    private MultipartFile img;

    public RecruitPostEntity getPostEntity(UserEntity user){


        return RecruitPostEntity.builder()
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
//                .tagList()
//                .img(awsS3.save(img))
                .build();
    }
}
