package hanghae7e6.prototype.recruitposttag;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecruitPostTagService {

    RecruitPostTagRepository recruitPostTagRepository;

    TagService tagService;

    @Autowired
    public RecruitPostTagService(RecruitPostTagRepository recruitPostTagRepository,
                                 TagService tagService) {
        this.recruitPostTagRepository = recruitPostTagRepository;
        this.tagService = tagService;
    }


    public List<TagEntity> getTagsByPostId(Long postId) {
        List <RecruitPostTagEntity> recruitPostTagEntities = recruitPostTagRepository.findAllByRecruitPostId(postId);
        return recruitPostTagEntities.stream().map(RecruitPostTagEntity::getTag).collect(Collectors.toList());
    }

    public List<RecruitPostTagEntity> saveTags(
            RecruitPostEntity recruitPostEntity, List<Long> tagIds){

        if(Objects.isNull(tagIds) || tagIds.isEmpty()){
            return Arrays.asList();
        }

        List<TagEntity> tags = tagIds.stream()
                .map(TagEntity::new)
                .collect(Collectors.toList());

        List<RecruitPostTagEntity> recruitPostTagEntities =
                RecruitPostTagDto.getEntities(recruitPostEntity, tags);

        return recruitPostTagRepository.saveAll(recruitPostTagEntities);
    }
}
