package hanghae7e6.prototype.recruitpost.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostParamDto{

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    @Min(value = 0, message = PostDtoMessage.INVALID_PARAMETER)
    @Max(value = 20, message = PostDtoMessage.INVALID_PARAMETER)
    private int limit;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private int page;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private int sort;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private Long tagId;

    @Builder
    public PostParamDto(int limit, int page, int sort, Long tagId){
        this.limit = limit;
        this.page = page-1;
        this.sort = sort;
        this.tagId = tagId;
    }

    public static void validate(@Valid PostParamDto requestDto){
    }
}