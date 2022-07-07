package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagService;
import hanghae7e6.prototype.tag.TagValue;
import hanghae7e6.prototype.user.CustomUserDetails;
import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecruitPostService {

    RecruitPostRepository recruitPostRepository;
    RecruitPostTagService recruitPostTagService;

    // UserService가 구현되지 않아서 임시적으로 사용
    @Autowired
    UserRepository userRepository;


    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository,
                              RecruitPostTagService recruitPostTagService) {
        this.recruitPostRepository = recruitPostRepository;
        this.recruitPostTagService = recruitPostTagService;
    }


    @Transactional(readOnly = true)
    public Page<RecruitPostEntity> getPosts(
            PostParamDto requestDto) {

        Sort sort = SortValue.getSort(requestDto.getSort());
        Pageable pageable = PageRequest.of(
                requestDto.getPage(), requestDto.getLimit(), sort);

        if(requestDto.getTagId().equals(TagValue.ALL.getTagId())){
            return recruitPostRepository.findAll(pageable);
        }


        List<Long> postIds = recruitPostTagService.findByTagId(requestDto.getTagId()).stream()
                .map(RecruitPostTagEntity::getRecruitPost)
                .map(RecruitPostEntity::getId)
                .collect(Collectors.toList());

        return recruitPostRepository.findAllByIdIn(postIds, pageable);


    }


    @Transactional(readOnly = true)
    public RecruitPostEntity getPost(Long postId) {
        return recruitPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("no data"));

    }


    @Transactional
    public RecruitPostEntity createPost(
            CustomUserDetails userDetails,
            PostRequestDto requestDto) {

        UserEntity user = userRepository.findById(userDetails.getId())
                .orElseThrow(IllegalArgumentException::new);

        RecruitPostEntity entity = requestDto.getPostEntity(user, recruitPostTagService);

        return recruitPostRepository.save(entity);
    }


    @Transactional
    public RecruitPostEntity updatePost(
            CustomUserDetails userDetails,
            Long postId,
            PostRequestDto requestDto) {

        RecruitPostEntity post = recruitPostRepository.findByIdAndUserId(postId, userDetails.getId())
                .orElseThrow(IllegalArgumentException::new);

        return post.updateFields(requestDto);
    }


    @Transactional(readOnly = true)
    public RecruitPostEntity findByIdAndUserId(
            CustomUserDetails userDetails,
            Long postId) {

        return recruitPostRepository.findByIdAndUserId(postId, userDetails.getId())
                .orElseThrow(IllegalArgumentException::new);
    }


    @Transactional
    public RecruitPostEntity deletePost(
            CustomUserDetails userDetails, Long postId){

        RecruitPostEntity post = recruitPostRepository.findByIdAndUserId(postId, userDetails.getId())
                .orElseThrow(IllegalArgumentException::new);

        recruitPostRepository.deleteById(post.getId());

        return post;
    }
}

