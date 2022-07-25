package hanghae7e6.prototype.applicant;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {

    Optional <ApplicantEntity> findByUserIdAndRecruitPostId(Long userId, Long recruitPostId);

    List<ApplicantEntity> findAllByRecruitPostId(Long recruitPostId);
}
