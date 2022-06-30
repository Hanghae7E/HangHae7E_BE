package hanghae7e6.prototype.recruitpost;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimplePostResponseDto {

    private String username;

    private String title;

    private String authorImage;

    private String projectImage;

    private LocalDateTime projectStartTime;

    private LocalDateTime projectEndTime;

    private List<String> tags;

    public SimplePostResponseDto(RecruitPostEntity post){
//        this.username = entity.
//        this.authorImage = entity.getUser().getImage();
        this.title = post.getTitle();
        this.projectImage = post.getImageUrl();
        this.projectStartTime = post.getProjectStartTime();
        this.projectEndTime = post.getProjectEndTime();
//        this.tags = post.getTags();
    }


    public static List<SimplePostResponseDto> getDtos(Page<RecruitPostEntity> posts){
        return posts.get()
                .map(SimplePostResponseDto::new)
                .collect(Collectors.toList());
    }
}
