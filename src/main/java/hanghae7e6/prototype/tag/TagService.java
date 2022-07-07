package hanghae7e6.prototype.tag;

import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    TagRepository tagRepository;

    @Autowired
    public TagService(
            TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public List<TagEntity> createTags(){
        List<TagEntity> tags = Stream.of(TagValue.values())
                .map(TagEntity::new)
                .collect(Collectors.toList());

        return tagRepository.saveAll(tags);
    }

    public List<TagEntity> findTagsById(List<Long> tagIds){
            return tagRepository.findAllById(tagIds);
    }

    public List<TagEntity> findAll(){
        return tagRepository.findAll();
    }

    public void insertTag(TagRequest tagRequest) throws AbstractException {
        if (tagRepository.existsByBody(tagRequest.getBody())) throw new InvalidException(ErrorCode.TAG_ALREADY_EXISTS);

        tagRepository.save(TagEntity.builder().body(tagRequest.getBody()).build());
    }
}
