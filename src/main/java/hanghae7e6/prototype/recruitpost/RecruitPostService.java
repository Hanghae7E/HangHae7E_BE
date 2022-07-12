package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagService;
import hanghae7e6.prototype.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecruitPostService {

    RecruitPostRepository recruitPostRepository;
    RecruitPostRepositoryCustom recruitPostRepositoryCustom;
    RecruitPostTagService recruitPostTagService;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository,
                              RecruitPostRepositoryCustom recruitPostRepositoryCustom,
                              RecruitPostTagService recruitPostTagService) {
        this.recruitPostRepository = recruitPostRepository;
        this.recruitPostRepositoryCustom = recruitPostRepositoryCustom;
        this.recruitPostTagService = recruitPostTagService;
    }


    @Transactional(readOnly = true)
    public Map<String, Object> getPosts(
            PostParamDto requestDto) {

        Map<String, Object> result = new HashMap<>();

        List<SimplePostResponseDto> posts = recruitPostRepositoryCustom.findAllByTagId(requestDto);
        boolean isLast = recruitPostRepositoryCustom.isLastPage(requestDto);

        result.put("posts", posts);
        result.put("isLast", isLast);

        return result;
    }


    @Transactional(readOnly = true)
    public DetailPostResponseDto getPost(Long postId) {

        return recruitPostRepositoryCustom.findById(postId);
    }


    @Transactional
    public RecruitPostEntity createPost(
            CustomUserDetails userDetails,
            PostRequestDto requestDto) {


        RecruitPostEntity post = recruitPostRepository.save(
                requestDto.getEntity(userDetails.getId()));

        recruitPostTagService.saveTags(post, requestDto.getTags());

        return post;

    }


    @Transactional
    public RecruitPostEntity updatePost(
            CustomUserDetails userDetails,
            Long postId,
            PostRequestDto requestDto) {

        RecruitPostEntity post =
                recruitPostRepositoryCustom.findByIdAndUserId(postId, userDetails.getId());

        return post.updateFields(requestDto);
    }


    @Transactional
    public RecruitPostEntity deletePost(
            CustomUserDetails userDetails, Long postId) {

        RecruitPostEntity post =
                recruitPostRepositoryCustom.findByIdAndUserId(postId, userDetails.getId());

        recruitPostRepository.deleteById(post.getId());

        return post;
    }
}

