package hanghae7e6.prototype.recruitpost.dto;

import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostParamDto {

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    @Min(value = 1, message = PostDtoMessage.INVALID_PARAMETER)
    @Max(value = 10, message = PostDtoMessage.INVALID_PARAMETER)
    private int limit;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private int page;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private int sort;

    @NotBlank(message = PostDtoMessage.EMPTY_PARAMETER)
    private String tag;

    public static void validate(@Valid PostParamDto requestDto){
    }
}
