package hanghae7e6.prototype.applicant;

import hanghae7e6.prototype.applicant.dto.ApplicantRequest;
import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import hanghae7e6.prototype.profile.service.PositionService;
import hanghae7e6.prototype.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruitPost")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/{postId}/application")
    public void createUserApplication(@PathVariable Long postId, @RequestBody ApplicantRequest applicantRequest, @AuthenticationPrincipal
        CustomUserDetails userDetails) {

        if (userDetails == null) throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        applicantRequest.setPostId(postId);
        applicantRequest.setUserId(userDetails.getId());


        applicantService.createApplicant(applicantRequest);

    }

//    @PutMapping ("/{postId}/application")
//    public void updateApplicant(@PathVariable Long postId, @ModelAttribute ApplicantRequest applicantRequest, @AuthenticationPrincipal
//        CustomUserDetails userDetails) throws AbstractException {
//
//        applicantRequest.setPostId(postId);
//        applicantRequest.setUserId(userDetails.getId());
//        applicantService.updateApplicant(applicantRequest);
//    }

    @PostMapping("/{postId}/application/accepted")
    public void acceptApplicant(@PathVariable Long postId, @RequestBody ApplicantRequest applicantRequest, @AuthenticationPrincipal
        CustomUserDetails userDetails) throws AbstractException {

        if (userDetails == null) throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        Long authorId = userDetails.getId();

        if (applicantRequest.getUserId() == null) throw new InvalidException(ErrorCode.EMPTY_BODY);

        applicantRequest.setPostId(postId);
        applicantRequest.setStatus("합격");
        applicantService.updateApplicant(authorId, applicantRequest);
    }

    @PostMapping("/{postId}/application/denied")
    public void deniedApplicant(@PathVariable Long postId, @RequestBody ApplicantRequest applicantRequest, @AuthenticationPrincipal
        CustomUserDetails userDetails) throws AbstractException {

        if (userDetails == null) throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        Long authorId = userDetails.getId();

        if (applicantRequest.getUserId() == null) throw new InvalidException(ErrorCode.EMPTY_BODY);

        applicantRequest.setPostId(postId);
        applicantRequest.setStatus("불합격");
        applicantService.updateApplicant(authorId, applicantRequest);
    }

    @DeleteMapping("/{postId}/application")
    public void deleteApplicant(@PathVariable Long postId, ApplicantRequest applicantRequest, @AuthenticationPrincipal
        CustomUserDetails userDetails) throws AbstractException {

        applicantRequest.setPostId(postId);
        applicantRequest.setUserId(userDetails.getId());
        applicantService.deleteApplicant(applicantRequest);
    }
}
