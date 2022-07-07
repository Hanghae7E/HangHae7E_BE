package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.tag.TagResponseDto;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private int totalMemberCount;

    private List<TagResponseDto> tags;

    private List<?> applicants;

}
