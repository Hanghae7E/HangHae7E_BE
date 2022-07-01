package hanghae7e6.prototype.service;

import hanghae7e6.prototype.domain.entity.UserEntity;
import hanghae7e6.prototype.dto.CustomUserDetails;
import hanghae7e6.prototype.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                                    .findById(Long.valueOf(userId))
                                    .orElseThrow(
                                        () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return CustomUserDetails.toUserDetails(userEntity);
    }
}
