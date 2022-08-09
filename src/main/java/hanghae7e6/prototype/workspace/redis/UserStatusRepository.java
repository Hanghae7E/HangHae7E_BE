package hanghae7e6.prototype.workspace.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserStatusRepository extends CrudRepository<UserStatusEntity, String> {
    List<UserStatusEntity> findAllByUuid(String uuid);
    Optional<UserStatusEntity> findByUserIdAndUuid(Long userId, String uuid);
    Optional<UserStatusEntity> deleteByUserIdAndUuid(Long userId, String uuid);
}
