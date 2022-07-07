package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.tag.TagResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import hanghae7e6.prototype.user.UserEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

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

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    private List<TagResponseDto> tags;

}
