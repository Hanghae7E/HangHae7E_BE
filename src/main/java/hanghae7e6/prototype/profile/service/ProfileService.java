package hanghae7e6.prototype.profile.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.profile.dto.ProfileRequest;
import hanghae7e6.prototype.profile.dto.ProfileResponse;
import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.entity.ProfileTagEntity;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.profile.repository.ProfileTagRepository;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostService;
import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserRepository;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileTagService profileTagService;
    private final ProfileRepository profileRepository;
    private final ProfileTagRepository profileTagRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final RecruitPostService recruitPostService;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String BUCKET;

    public ProfileResponse getUserProfile(Long userId) throws AbstractException {
        ProfileEntity profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(
                                                     ErrorCode.USER_NOT_FOUND));

        List<ProfileTagEntity> profileTags =
            profileTagRepository.findAllByProfileId(profile.getId());

        List <RecruitPostEntity> myRecruitPosts = recruitPostService.getMyPostsByUserId(userId);
        List <RecruitPostEntity> myAppliedPosts = recruitPostService.getAppliedPostsByUserId(userId);


        return ProfileResponse.toResponse(profile, profileTags, myRecruitPosts, myAppliedPosts);
    }

    @Transactional
    public void updateUserProfile(Long userId, ProfileRequest profileRequest) throws AbstractException, IOException {
        ProfileEntity profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        if (profileRequest.getUsername() != null && !profileRequest.getUsername().equals(user.getUsername()))
            user.setUsername(profileRequest.getUsername());

        Long profileId = profile.getId();

        PositionEntity updatedPosition = positionRepository.findByPositionName(profileRequest.getPosition());
        if (profileRequest.getPosition() != null && updatedPosition == null) throw new InvalidException(ErrorCode.INVALID_POSITION);

        List <ProfileTagEntity> profileTags = profile.getProfileTags();

        profileTagService.updateProfileTags(profileTags, profileRequest.getFields(),
            profileRequest.getSkills(), profile);

        if (profileRequest.getFiles() != null) {
            uploadMultipartFileToS3(profileRequest.getFiles(), toS3ProfileImgKey(profileId));
            String profileImgUrl = amazonS3Client.getUrl(BUCKET, toS3ProfileImgKey(profileId)).toString();

            profile.setImageUrl(profileImgUrl);
        }

        profile.update(profileRequest, updatedPosition);

        userRepository.save(user);
        profileRepository.save(profile);
    }

    private void uploadMultipartFileToS3(MultipartFile file, String key) throws IOException {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType("image/jpeg");

        amazonS3Client.putObject(BUCKET, key, file.getInputStream(), objectMetadata);
    }

    private String toS3ProfileImgKey(Long profileId) {
        return "images/profiles/" + profileId.toString() + "/profileImage.jpg";
    }
}
