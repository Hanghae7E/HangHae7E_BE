package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.domain.entity.UserEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimplePostResponseDto {

    private Long postId;

    private String username;

    private String introduce;

    private String userPosition;

    private String authorImage;

    private String title;

    private String projectImage;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    private List<TagResponseDto> tags;

    public SimplePostResponseDto(RecruitPostEntity post){
        UserEntity user = post.getUser();

//        this.username = user.getUsername();
//        this.authorImage = post.getUser().getImage();
//        this.introduce = post.
//        this.userPosition
        this.postId = post.getId();
        this.title = post.getTitle();
        this.projectImage = post.getImageUrl();
        this.projectStartTime = post.getProjectStartTime();
        this.projectEndTime = post.getProjectEndTime();
        this.recruitDueTime = post.getRecruitDueTime();

//        this.tags = TagResponseDto.getDtos(post);
    }


    public static List<SimplePostResponseDto> getDtos(Page<RecruitPostEntity> posts){
        return posts.get()
                .map(SimplePostResponseDto::new)
                .collect(Collectors.toList());
    }
}
