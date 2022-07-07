package hanghae7e6.prototype.profile.controller;

import hanghae7e6.prototype.profile.dto.ProfileRequest;
import hanghae7e6.prototype.profile.dto.ProfileResponse;
import hanghae7e6.prototype.profile.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, @RequestBody ProfileRequest profileRequest) {
        profileService.updateUserProfile(userId, profileRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
