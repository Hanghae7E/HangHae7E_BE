package hanghae7e6.prototype.recruitposttag;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostTagRepository extends JpaRepository<RecruitPostTagEntity, Long> {
}
