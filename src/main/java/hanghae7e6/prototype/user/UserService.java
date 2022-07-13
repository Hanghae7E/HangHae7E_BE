package hanghae7e6.prototype.user;

import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity getUserById(Long userId) throws AbstractException {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(
            ErrorCode.USER_NOT_FOUND));
    }

}
