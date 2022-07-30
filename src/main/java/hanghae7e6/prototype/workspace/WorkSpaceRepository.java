package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.project.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkSpaceRepository extends JpaRepository<WorkSpaceEntity, Long> {
    Optional<WorkSpaceEntity> findByProjectAndId(ProjectEntity projectEntity, Long id);
    Optional<WorkSpaceEntity> deleteByProjectAndId(ProjectEntity projectEntity, Long id);
    Page<WorkSpaceEntity> findAllByProject(ProjectEntity projectEntity, Pageable pageable);
    Page<WorkSpaceEntity> findAllByProjectAndTitleContains(ProjectEntity projectEntity, Pageable pageable, String title);
}
