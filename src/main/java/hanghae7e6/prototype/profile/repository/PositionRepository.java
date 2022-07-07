package hanghae7e6.prototype.profile.repository;

import hanghae7e6.prototype.profile.entity.PositionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    Optional<PositionEntity> findByPositionName(String positionName);
}
