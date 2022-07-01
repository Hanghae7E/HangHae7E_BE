package hanghae7e6.prototype.repository;

import hanghae7e6.prototype.domain.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findBySocialTypeAndEmail(String socialType, String email);

    boolean existsByEmail(String email);
}
