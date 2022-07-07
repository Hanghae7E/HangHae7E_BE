package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {

    @NotBlank(message = PostDtoMessage.EMPTY_TITLE)
    private String title;

    @NotBlank(message = PostDtoMessage.EMPTY_BODY)
    private String body;

    @NotEmpty(message = PostDtoMessage.INVALID_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
            message = PostDtoMessage.INVALID_DATE)
    private String projectStartTime;

    @NotEmpty(message = PostDtoMessage.INVALID_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
            message = PostDtoMessage.INVALID_DATE)
    private String projectEndTime;

    @NotEmpty(message = PostDtoMessage.INVALID_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
            message = PostDtoMessage.INVALID_DATE)
    private String recruitDueTime;

    @Min(value = 1, message = PostDtoMessage.INVALID_NUMBER)
    private Integer totalMemberCount;

    private List<Long> tags;

    private MultipartFile img;


    public LocalDate getProjectStartTime() {
        return getLocalDate(projectStartTime);
    }

    public LocalDate getProjectEndTime() {
        return getLocalDate(projectEndTime);
    }

    public LocalDate getRecruitDueTime() {
        return getLocalDate(recruitDueTime);
    }

    private LocalDate getLocalDate(String date){
        return Objects.nonNull(date)?
                LocalDate.parse(date) : null;
    }

    public RecruitPostEntity getEntity(Long userId){

        return RecruitPostEntity.builder()
                .user(UserEntity.builder().id(userId).build())
                .title(getTitle())
                .body(getBody())
                .projectStartTime(getProjectStartTime())
                .projectEndTime(getProjectEndTime())
                .recruitDueTime(getRecruitDueTime())
                .totalMemberCount(getTotalMemberCount())
                .build();
    }
}
