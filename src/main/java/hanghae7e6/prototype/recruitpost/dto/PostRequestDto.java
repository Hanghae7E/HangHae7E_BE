package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.user.UserEntity;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.persistence.Column;
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

    private String tags;

    private MultipartFile img;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private String previousImgUrl;


    public Object getPreviousImgUrl() {return this.previousImgUrl; }

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

    public RecruitPostEntity toEntity(UserEntity user, ProfileEntity profile){

        return RecruitPostEntity.builder()
                .user(user)
                .title(getTitle())
                .body(getBody())
                .profile(profile)
                .projectStartTime(getProjectStartTime())
                .projectEndTime(getProjectEndTime())
                .recruitDueTime(getRecruitDueTime())
                .requiredDesigners(getRequiredDesigners())
                .requiredDevelopers(getRequiredDevelopers())
                .requiredProjectManagers(getRequiredProjectManagers())
                .build();
    }

    public List<Long> getParsedTags() {
        StringTokenizer st = new StringTokenizer(this.tags, ",");
        List <Long> parsedTags = new ArrayList<>();

        while (st.hasMoreTokens())
            parsedTags.add(Long.parseLong(st.nextToken()));

        return parsedTags;
    }
}
