package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.tempteam.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkSpaceRepository extends JpaRepository<WorkSpaceEntity, Long> {
    Optional<WorkSpaceEntity> findByTeamAndId(Team team, Long id);
    Optional<WorkSpaceEntity> deleteByTeamAndId(Team team, Long id);
    Page<WorkSpaceEntity> findAllByTeam(Team team, Pageable pageable);
}
