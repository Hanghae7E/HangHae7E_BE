package hanghae7e6.prototype.profile.service;

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
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileTagService profileTagService;

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ProfileTagRepository profileTagRepository;

    @Autowired
    PositionRepository positionRepository;



    public ProfileResponse getUserProfile(Long userId) throws AbstractException {
        ProfileEntity profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(
                                                     ErrorCode.USER_NOT_FOUND));

        List<ProfileTagEntity> profileTags =
            profileTagRepository.findAllByProfileId(profile.getId());

        return ProfileResponse.toResponse(profile, profileTags);
    }

    @Transactional
    public void updateUserProfile(Long userId, ProfileRequest profileRequest) throws AbstractException {
        ProfileEntity profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        PositionEntity updatedPosition = positionRepository.findByPositionName(profileRequest.getPosition())
                                                           .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_POSITION));

        List <ProfileTagEntity> profileTags = profile.getProfileTags();

        profileTagService.updateProfileTags(profileTags, profileRequest.getFields(),
            profileRequest.getSkills(), profile);

        profile.update(profileRequest, updatedPosition);

        profileRepository.save(profile);
    }

}
