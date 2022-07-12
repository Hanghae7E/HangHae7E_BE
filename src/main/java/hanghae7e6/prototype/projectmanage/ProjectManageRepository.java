package hanghae7e6.prototype.projectmanage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectManageRepository extends JpaRepository<ProjectManageEntity,Long> {
    //@Overrride
    Optional<ProjectManageEntity> findById(Long projectId);
}
