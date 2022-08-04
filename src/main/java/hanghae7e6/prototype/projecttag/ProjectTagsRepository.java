package hanghae7e6.prototype.projecttag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTagsRepository extends JpaRepository<ProjectTagsEntity, Long> {
}
