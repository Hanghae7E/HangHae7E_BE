package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecruitPostService {

    RecruitPostRepository recruitPostRepository;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository){
        this.recruitPostRepository = recruitPostRepository;
    }


    @Transactional(readOnly = true)
    public List<SimplePostResponseDto> getPosts(
            PostParamDto requestDto){

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
    public DetailPostResponseDto getPost(Long postId){
        RecruitPostEntity post = recruitPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("no data"));

        return new DetailPostResponseDto(post);
    }


    @Transactional
    public RecruitPostEntity createPost(
//        UserDetails userDetails,
            PostRequestDto requestDto){

        RecruitPostEntity entity = requestDto.getPostEntity();
        return recruitPostRepository.save(entity);
    }


//    @Transactional
//    public RecruitPostEntity updatePost(
////        UserDetails userDetails,
//            Long postId,
//            PostRequestDto requestDto){
//
//    }
}

