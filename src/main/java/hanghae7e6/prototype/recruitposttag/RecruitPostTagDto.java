package hanghae7e6.prototype.recruitposttag;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RecruitPostTagDto {

    private Long postId;

    private Long tagId;

    public static RecruitPostTagEntity getEntity(RecruitPostEntity recruitPost, TagEntity tag){
        return RecruitPostTagEntity.builder()
                .tag(tag)
                .recruitPost(recruitPost)
                .build();
    }

    public static List<RecruitPostTagEntity> getEntities(
            RecruitPostEntity recruitPost, List<TagEntity> tags){

        return tags.stream()
                .map((tag) -> RecruitPostTagDto.getEntity(recruitPost, tag))
                .collect(Collectors.toList());
    }


}
