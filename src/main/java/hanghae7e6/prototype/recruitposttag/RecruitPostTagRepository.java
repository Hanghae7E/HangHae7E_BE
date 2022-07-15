package hanghae7e6.prototype.recruitposttag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecruitPostTagRepository extends JpaRepository<RecruitPostTagEntity, Long> {

    @Query(value = "select rt from RecruitPostTagEntity rt join fetch rt.tag where rt.recruitPost.id = :recruitPostId")
    List <RecruitPostTagEntity> findAllByRecruitPostId(Long recruitPostId);

    @Query(value = "select rt from RecruitPostTagEntity rt join fetch rt.recruitPost join fetch rt.tag where rt.tag.id = :tagId")
    List<RecruitPostTagEntity> findAllByTagId(Long tagId);
}
