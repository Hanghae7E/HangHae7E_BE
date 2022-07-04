package hanghae7e6.prototype.recruitposttag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitPostTagRepository extends JpaRepository<RecruitPostTagEntity, Long> {
    List<RecruitPostTagEntity> findAllByTagId(Long tagId);
}
