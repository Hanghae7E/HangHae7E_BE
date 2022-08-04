package hanghae7e6.prototype.project;

import hanghae7e6.prototype.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query("select distinct p from ProjectEntity p left join p.projectTags pt " +
            "left join pt.tag t left join p.projectMembers pm left join pm.user u "
    +" left join u.profile pf where p.projectId = ?1")
    Optional<ProjectEntity> detailJoinById(Long id);

    @Query("select distinct p from ProjectEntity p inner join p.projectMembers pm " +
            "on pm.user.id = ?1")
    Page<ProjectEntity> simpleJoinByUserId(Long userId, Pageable pageable);
}
