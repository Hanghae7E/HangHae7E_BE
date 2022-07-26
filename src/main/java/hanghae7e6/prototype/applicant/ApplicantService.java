package hanghae7e6.prototype.applicant;

import hanghae7e6.prototype.applicant.dto.ApplicantRequest;
import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.profile.service.PositionService;
import hanghae7e6.prototype.profile.service.ProfileService;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostService;
import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final RecruitPostService recruitPostService;
    private final PositionService positionService;
    private final ProfileService profileService;

    @Transactional
    public void createApplicant(ApplicantRequest applicantRequest) throws AbstractException {
        Long postId = applicantRequest.getPostId();
        Long userId = applicantRequest.getUserId();

        if (applicantRepository.findByUserIdAndRecruitPostId(userId, postId).isPresent())
            throw new InvalidException(ErrorCode.APPLICANT_ALREADY_EXISTS);

        if (applicantRequest.getPosition() == null) {
            String positionName = profileService.getUserProfile(userId).getPosition();
            if (positionName.equals("")) throw new InvalidException(ErrorCode.APPLICANT_POSITION_EMPTY);
            applicantRequest.setPosition(positionName);
        }

        UserEntity user = userService.getUserById(userId);
        RecruitPostEntity recruitPost = recruitPostService.getPostById(postId);

        if (Objects.equals(userId, recruitPost.getUser().getId())) throw new InvalidException(ErrorCode.APPLICANT_IS_AUTHOR);

        applicantRepository.save(applicantRequest.toEntity(user, recruitPost));
    }


    @Transactional
    public void updateApplicant(Long authorId, ApplicantRequest applicantRequest) throws AbstractException {
        Long postId = applicantRequest.getPostId();
        Long userId = applicantRequest.getUserId();

        RecruitPostEntity recruitPost = recruitPostService.getPostById(postId);

        if (recruitPost.getUser().getId() != authorId) throw new InvalidException(ErrorCode.NOT_AUTHOR);
        if (recruitPost.getUser().getId() == userId) throw new InvalidException(ErrorCode.APPLICANT_IS_AUTHOR);

        ApplicantEntity applicant = applicantRepository.findByUserIdAndRecruitPostId(userId, postId).orElseThrow(() ->  new NotFoundException(ErrorCode.APPLICANT_NOT_FOUND));

        if (applicantRequest.getPosition() == null) {
            String positionName = profileService.getUserProfile(userId).getPosition().toString();
            applicantRequest.setPosition(positionName);
        }

        applicant.update(applicantRequest);
    }


    @Transactional
    public void deleteApplicant(ApplicantRequest applicantRequest) throws AbstractException {
        Long postId = applicantRequest.getPostId();
        Long userId = applicantRequest.getUserId();

        ApplicantEntity applicant = applicantRepository.findByUserIdAndRecruitPostId(userId, postId).orElseThrow(() ->  new NotFoundException(ErrorCode.APPLICANT_NOT_FOUND));

        applicantRepository.delete(applicant);
    }
}
