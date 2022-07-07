package hanghae7e6.prototype.user;

import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @PostMapping
    public void insertUser(@RequestBody UserRequest userRequest) {
        UserEntity saved = userRepository.save(userRequest.toEntity());
        ProfileEntity profile = ProfileEntity.builder().user(saved).build();
        profileRepository.save(profile);
    }

    @GetMapping
    public List<UserEntity> getAllUser() {

        return userRepository.findAll();
    }
}
