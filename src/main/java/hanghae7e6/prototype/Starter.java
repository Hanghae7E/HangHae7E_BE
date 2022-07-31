package hanghae7e6.prototype;

import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserRepository;
import hanghae7e6.prototype.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Starter {

    UserRepository userRepository;

    @Autowired
    public Starter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void doInit(){
        UserEntity userB = UserEntity.builder()
                .email("test@test.com")
                .username("tester")
                .userRole(UserRole.USER)
                .socialType("testType")
                .build();

        UserEntity user = userRepository.save(userB);

    }

}
