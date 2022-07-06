package hanghae7e6.prototype.recruitpost;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPostEntity, Long> {
    Optional<RecruitPostEntity> findByIdAndUserId(Long postId, Long userId);

    Page<RecruitPostEntity> findAllByIdIn(List<Long> postIds, Pageable pageable);
}
