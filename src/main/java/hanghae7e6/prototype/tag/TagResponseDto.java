package hanghae7e6.prototype.tag;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponseDto {

    private Long tagId;

    private String body;

    public TagResponseDto(TagEntity tag){
        this.tagId = tag.getId();
        this.body = tag.getBody();
    }


    public static List<TagResponseDto> getDtos(List<TagEntity> tags){
        return tags.stream()
                .map(TagResponseDto::new)
                .collect(Collectors.toList());
    }


    public static List<TagResponseDto> getDtos(RecruitPostEntity post){
        return TagResponseDto.getDtos(
                post.getRecruitPostTag().stream()
                .map(RecruitPostTagEntity::getTag)
                .collect(Collectors.toList()));
    }


}
