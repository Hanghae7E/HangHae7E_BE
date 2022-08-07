package hanghae7e6.prototype.project.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface UserStatusRepository extends CrudRepository<UserStatusEntity, Long> {
    List<UserStatusEntity> findAllByUuid(String uuid);
}
