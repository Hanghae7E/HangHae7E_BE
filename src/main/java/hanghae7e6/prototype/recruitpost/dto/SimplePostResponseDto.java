package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimplePostResponseDto {

    private Long postId;

    private String username;

    private String introduce;

    private String userPosition;

    private String authorImage;

    private String title;

    private String projectImage;

    private LocalDateTime projectStartTime;

    private LocalDateTime projectEndTime;

    private LocalDateTime recruitDueTime;

    private List<String> tags;

    public SimplePostResponseDto(RecruitPostEntity post){
//        this.username = post.
//        this.authorImage = post.getUser().getImage();
//        this.introduce = post.
//        this.userPosition
        this.postId = post.getId();
        this.title = post.getTitle();
        this.projectImage = post.getImageUrl();
        this.projectStartTime = post.getProjectStartTime();
        this.projectEndTime = post.getProjectEndTime();
        this.recruitDueTime = post.getRecruitDueTime();
//        this.tags = post.getTags();
    }


    public static List<SimplePostResponseDto> getDtos(Page<RecruitPostEntity> posts){
        return posts.get()
                .map(SimplePostResponseDto::new)
                .collect(Collectors.toList());
    }
}
