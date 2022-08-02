package hanghae7e6.prototype.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query("select distinct p from ProjectEntity p left join p.projectTags pt " +
            "left join pt.tag t left join p.projectMembers pm left join pm.user u "
    +" left join u.profile pf")
    Optional<ProjectEntity> joinById(Long id);
}
