package hanghae7e6.prototype.profile.controller;

import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import hanghae7e6.prototype.exception.UnAuthorizedException;
import hanghae7e6.prototype.profile.dto.ProfileRequest;
import hanghae7e6.prototype.profile.dto.ProfileResponse;
import hanghae7e6.prototype.profile.service.ProfileService;
import hanghae7e6.prototype.user.CustomUserDetails;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class ProfileController {

    final
    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getUserProfile(@PathVariable Long userId) {
        ProfileResponse profileResponse = profileService.getUserProfile(userId);

        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable Long userId, @ModelAttribute ProfileRequest profileRequest) throws IOException, AbstractException {

        if (userDetails == null) throw new UnAuthorizedException(ErrorCode.LOGIN_REQUIRED);
        if (userDetails.getId() != userId) throw new InvalidException(ErrorCode.INVALID_REQUEST);

        profileService.updateUserProfile(userId, profileRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
