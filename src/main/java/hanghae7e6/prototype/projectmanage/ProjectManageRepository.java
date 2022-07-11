package hanghae7e6.prototype.projectmanage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectManageRepository extends JpaRepository<ProjectManageEntity,Long> {

}
