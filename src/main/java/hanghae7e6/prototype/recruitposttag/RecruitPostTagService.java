package hanghae7e6.prototype.recruitposttag;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<RecruitPostTagEntity> saveTags(
            RecruitPostEntity recruitPostEntity, List<Long> tagIds){

        List<TagEntity> tags = tagService.findTagsById(tagIds);

        List<RecruitPostTagEntity> recruitPostTagEntities =
                RecruitPostTagDto.getEntities(recruitPostEntity, tags);

        return recruitPostTagRepository.saveAll(recruitPostTagEntities);

    }


    public List<RecruitPostTagEntity> findByTagId(Long tagId){
        return recruitPostTagRepository.findAllByTagId(tagId);
    }

}
