package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {

    private String title;

    private String body;

    private String projectStartTime;

    private String projectEndTime;

    private String recruitDueTime;

    private int totalMemberCount;

    private List<String> tagList;

    private MultipartFile img;

    public RecruitPostEntity getPostEntity(){
        return RecruitPostEntity.builder()
                .title(title)
                .body(body)
                .projectStartTime(LocalDateTime.parse(projectStartTime))
                .projectEndTime(LocalDateTime.parse(projectEndTime))
                .recruitDueTime(LocalDateTime.parse(recruitDueTime))
                .totalMemderCount(totalMemberCount)
//                .tagList()
//                .img(awsS3.save(img))
                .build();
    }
}
