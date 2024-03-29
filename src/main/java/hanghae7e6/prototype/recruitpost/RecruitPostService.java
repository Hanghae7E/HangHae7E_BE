package hanghae7e6.prototype.recruitpost;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import hanghae7e6.prototype.applicant.ApplicantEntity;
import hanghae7e6.prototype.applicant.ApplicantRepository;
import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.exception.TransferException;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.profile.service.ProfileService;
import hanghae7e6.prototype.profile.service.ProfileTagService;
import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagService;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagResponseDto;
import hanghae7e6.prototype.tag.TagService;
import hanghae7e6.prototype.user.CustomUserDetails;
import hanghae7e6.prototype.user.UserEntity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;
    private final ProfileRepository profileRepository;
    private final ApplicantRepository applicantRepository;
    private final RecruitPostRepositoryCustom recruitPostRepositoryCustom;
    private final RecruitPostTagService recruitPostTagService;
    private final TagService tagService;
    private final AmazonS3Client amazonS3Client;
    private final ProfileTagService profileTagService;
    @Value("${cloud.aws.s3.bucket}") private String BUCKET;


    private SimplePostResponseDto transfer(RecruitPostEntity entity) throws AbstractException {
        SimplePostResponseDto response = SimplePostResponseDto.toDto(entity);
        List <TagResponseDto> authorFields = TagResponseDto
            .toDtos(profileTagService.getTagsByAttributeNameAndProfileId("field", entity.getProfile().getId()));

        List <TagResponseDto> tagRes = TagResponseDto.toDtos(recruitPostTagService.getTagsByPostId(entity.getId()));
        response.setTags(tagRes);
        response.setAuthorFields(authorFields);
        return response;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getPosts(PageRequest pageRequest, Long tagId) throws AbstractException {
        Map<String, Object> result = new HashMap<>();
        Page<RecruitPostEntity> recruitPostPage = tagId == null? recruitPostRepository.findAllByRecruitStatusAndRecruitDueTimeGreaterThan(Boolean.TRUE,
            LocalDate.now().minusDays(1) , pageRequest) : recruitPostRepository.findAllByTagIdAndRecruitStatusAndRecruitDueTimeGreaterThan(tagId,
            Boolean.TRUE, LocalDate.now().minusDays(1),  pageRequest);
        List<RecruitPostEntity> recruitPosts = recruitPostPage.getContent();

        List <SimplePostResponseDto> responseDtos = recruitPosts.stream().map(this::transfer).collect(Collectors.toList());
        result.put("posts", responseDtos);
        result.put("isLast", !recruitPostPage.hasNext());

        return result;
    }


    @Transactional(readOnly = true)
    public DetailPostResponseDto getPost(Long currentUserId, Long postId) throws AbstractException {
        RecruitPostEntity recruitPost = recruitPostRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
        List <TagEntity> tags = recruitPostTagService.getTagsByPostId(recruitPost.getId());

        if (currentUserId != null && currentUserId == recruitPost.getUser().getId()) {
            List<ApplicantEntity> applicants = applicantRepository.findAllByRecruitPostId(recruitPost.getId());
            return DetailPostResponseDto.toDto(recruitPost, tags, applicants);
        }

        return DetailPostResponseDto.toDto(recruitPost, tags);
    }

    public RecruitPostEntity getPostById(Long postId) throws AbstractException {
        return recruitPostRepository.findById(postId).orElseThrow(() -> new NotFoundException(
            ErrorCode.BOARD_NOT_FOUND));
    }


    @Transactional
    public void createPost(
            CustomUserDetails userDetails,
            PostRequestDto requestDto) throws IOException, AbstractException {

        ProfileEntity profile = profileRepository.findByUserId(userDetails.getId())
                                                 .orElseThrow(() -> new NotFoundException(
                                                     ErrorCode.USER_NOT_FOUND));

        if (requestDto.getTitle() == null) throw new InvalidException(ErrorCode.EMPTY_BODY);


        RecruitPostEntity post = recruitPostRepository.save(requestDto.toEntity(profile.getUser(), profile));
        if (requestDto.getTags() != null && !requestDto.getTags().equals("")) {
            List<TagEntity> tags = tagService.getTagsByIds(requestDto.getParsedTags());
            post.setRecruitPostTag(tags);
        }

        deleteAndUploadImg(requestDto, post,  post.getId());
        recruitPostRepository.save(post);
    }


    @Transactional
    public void updatePost(CustomUserDetails userDetails, Long postId, PostRequestDto requestDto)
        throws IOException, AbstractException {

        RecruitPostEntity post = recruitPostRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));

        if (userDetails != null && userDetails.getId() != post.getUser().getId())
            throw new InvalidException(ErrorCode.NOT_AUTHOR);

        post.updateFields(requestDto);

        if (requestDto.getTags() != null && !requestDto.getTags().equals("")) {
            List<TagEntity> tags = tagService.getTagsByIds(requestDto.getParsedTags());
            post.setRecruitPostTag(tags);
        }

        deleteAndUploadImg(requestDto, post, postId);
        recruitPostRepository.save(post);
    }


    @Transactional
    public void deletePost(CustomUserDetails userDetails, Long postId) throws AbstractException {

        RecruitPostEntity post = recruitPostRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
        recruitPostRepository.deleteById(post.getId());

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

//        if (post.getImageUrl() != null && !post.getImageUrl().equals(""))
//            amazonS3Client.deleteObject(BUCKET, toS3ProfileImgKey(postId));

        uploadMultipartFileToS3(requestDto.getImg(), toS3ProfileImgKey(postId));
        String profileImgUrl = amazonS3Client.getUrl(BUCKET, toS3ProfileImgKey(postId)).toString();

        post.setImageUrl(profileImgUrl);
    }

    @Transactional
    public void closePost(CustomUserDetails userDetails, Long postId) throws AbstractException {
        RecruitPostEntity post = recruitPostRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
        if (post.getUser().getId() != userDetails.getId()) throw new InvalidException(ErrorCode.NOT_AUTHOR);
        if (post.getRecruitStatus() == Boolean.FALSE) throw new InvalidException(ErrorCode.ALREADY_CLOSED_POST);

        List <ApplicantEntity> applicants = applicantRepository.findAllByRecruitPostId(postId);
        applicants.stream().filter(applicant -> applicant.getStatus().equals("대기중"))
                            .forEach(applicant -> applicant.setStatus("불합격"));

        post.closeRecruitPost();
    }
}

