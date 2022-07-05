package hanghae7e6.prototype.recruitpost.dto;

import hanghae7e6.prototype.recruitpost.SortValue;
import hanghae7e6.prototype.tag.TagValue;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostParamDto{

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    @Min(value = 0, message = PostDtoMessage.INVALID_PARAMETER)
    @Max(value = 20, message = PostDtoMessage.INVALID_PARAMETER)
    private Integer limit = 6;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private Integer page = 0;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private Integer sort = 0;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private Long tagId = TagValue.ALL.getTagId();

    @Builder
    public PostParamDto(Integer limit, Integer page, Integer sort, Long tagId){
        this.limit = Objects.nonNull(limit)? limit : this.limit;
        this.page = Objects.nonNull(page)? page-1 : this.page;
        this.sort = Objects.nonNull(sort)? sort : this.sort;
        this.tagId = Objects.nonNull(tagId)? tagId : this.tagId;
    }

    public static void validate(@Valid PostParamDto requestDto){
    }
}