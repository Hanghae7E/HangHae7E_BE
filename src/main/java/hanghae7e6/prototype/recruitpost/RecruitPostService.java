package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.domain.entity.UserEntity;
import hanghae7e6.prototype.dto.CustomUserDetails;
import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RecruitPostService {

    RecruitPostRepository recruitPostRepository;

    // UserService가 구현되지 않아서 임시적으로 사용
    @Autowired
    UserRepository userRepository;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository) {
        this.recruitPostRepository = recruitPostRepository;
    }


    @Transactional(readOnly = true)
    public List<SimplePostResponseDto> getPosts(
            PostParamDto requestDto) {

        Page<RecruitPostEntity> posts;

//        if(tag.equals("all")){
//        }

        Sort sort = SortValue.getSort(requestDto.getSort());
        Pageable pageable = PageRequest.of(
                requestDto.getPage(), requestDto.getLimit(), sort);

        posts = recruitPostRepository.findAll(pageable);

        return SimplePostResponseDto.getDtos(posts);
    }


    @Transactional(readOnly = true)
    public DetailPostResponseDto getPost(Long postId) {
        RecruitPostEntity post = recruitPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("no data"));

        return new DetailPostResponseDto(post);
    }


    @Transactional
    public RecruitPostEntity createPost(
            CustomUserDetails userDetails,
            PostRequestDto requestDto) {


        UserEntity user = userRepository.findById(userDetails.getId())
                .orElseThrow(IllegalArgumentException::new);

        RecruitPostEntity entity = requestDto.getPostEntity(user);

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

