package hanghae7e6.prototype.user;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional <UserEntity> findBySocialTypeAndEmail(String socialType, String email);

    boolean existsByEmail(String email);
}
