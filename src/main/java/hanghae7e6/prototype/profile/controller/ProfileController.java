package hanghae7e6.prototype.profile.controller;

import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.profile.dto.ProfileRequest;
import hanghae7e6.prototype.profile.dto.ProfileResponse;
import hanghae7e6.prototype.profile.service.ProfileService;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, ProfileRequest profileRequest) throws IOException, AbstractException {
        profileService.updateUserProfile(userId, profileRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
