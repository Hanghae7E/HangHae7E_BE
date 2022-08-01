package hanghae7e6.prototype.profile.repository;

import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByUserId(Long userId);

    List<ProfileEntity> findAllByUserIn(List<UserEntity> user);
}
