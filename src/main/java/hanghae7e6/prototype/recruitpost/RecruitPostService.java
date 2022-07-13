package hanghae7e6.prototype.recruitpost;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagService;
import hanghae7e6.prototype.user.CustomUserDetails;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;
    private final RecruitPostRepositoryCustom recruitPostRepositoryCustom;
    private final RecruitPostTagService recruitPostTagService;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}") private String BUCKET;


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

    public RecruitPostEntity getPostById(Long postId) throws AbstractException {
        return recruitPostRepository.findById(postId).orElseThrow(() -> new NotFoundException(
            ErrorCode.BOARD_NOT_FOUND));
    }


    @Transactional
    public RecruitPostEntity createPost(
            CustomUserDetails userDetails,
            PostRequestDto requestDto) throws IOException {

        RecruitPostEntity post = recruitPostRepository.save(
                requestDto.getEntity(userDetails.getId()));
        if (requestDto.getTags() != null && !requestDto.getTags().equals(""))
            recruitPostTagService.saveTags(post, requestDto.getParsedTags());

        deleteAndUploadImg(requestDto, post,  post.getId());

        return post;

    }


    @Transactional
    public void updatePost(
            CustomUserDetails userDetails,
            Long postId,
            PostRequestDto requestDto) throws IOException {

        RecruitPostEntity post =
                recruitPostRepositoryCustom.findByIdAndUserId(postId, userDetails.getId());

        post.updateFields(requestDto);

        deleteAndUploadImg(requestDto, post, postId);
    }


    @Transactional
    public RecruitPostEntity deletePost(
            CustomUserDetails userDetails, Long postId) {

        RecruitPostEntity post =
                recruitPostRepositoryCustom.findByIdAndUserId(postId, userDetails.getId());

        if (post.getImageUrl() != null)
            amazonS3Client.deleteObject(BUCKET, toS3ProfileImgKey(postId));

        recruitPostRepository.deleteById(post.getId());

        return post;
    }

    public List<RecruitPostEntity> getMyPostsByUserId(Long userId) {
        return recruitPostRepository.findAllByUserId(userId);
    }

    public List<RecruitPostEntity> getAppliedPostsByUserId(Long userId) {
        return recruitPostRepository.findMyApplyPostsByUserId(userId);
    }


    private void uploadMultipartFileToS3(MultipartFile file, String key) throws IOException {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType("image/jpeg");

        amazonS3Client.putObject(BUCKET, key, file.getInputStream(), objectMetadata);
    }

    private String toS3ProfileImgKey(Long postId) {
        return "images/recruitPosts/" + postId.toString() + ".jpg";
    }

    @Transactional
    public void deleteAndUploadImg(PostRequestDto requestDto, RecruitPostEntity post, Long postId)
        throws IOException {
        if (requestDto.getImg() == null) {
            if (requestDto.getPreviousImgUrl() != null)
                post.setImageUrl((String) requestDto.getPreviousImgUrl());
             else
                 post.setImageUrl("");
            return;
        }

        if (post.getImageUrl() != null && !post.getImageUrl().equals(""))
            amazonS3Client.deleteObject(BUCKET, toS3ProfileImgKey(postId));

        uploadMultipartFileToS3(requestDto.getImg(), toS3ProfileImgKey(postId));
        String profileImgUrl = amazonS3Client.getUrl(BUCKET, toS3ProfileImgKey(postId)).toString();

        post.setImageUrl(profileImgUrl);
    }
}

