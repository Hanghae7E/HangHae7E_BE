package hanghae7e6.prototype.profile.repository;

import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    PositionEntity findByPositionName(String positionName);

}
