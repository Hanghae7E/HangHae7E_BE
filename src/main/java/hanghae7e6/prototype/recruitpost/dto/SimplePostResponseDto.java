package hanghae7e6.prototype.recruitpost.dto;

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

    private Boolean isLast = false;

    private List<Long> tags = new ArrayList<>();

    public void addTag(Long postTagDto){
        tags.add(postTagDto);
    }
}
