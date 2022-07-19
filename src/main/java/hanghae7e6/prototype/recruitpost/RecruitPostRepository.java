package hanghae7e6.prototype.recruitpost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPostEntity, Long>{

    @Query(value = "select distinct post from RecruitPostEntity post join fetch post.user left join fetch post.applicants where post.user.id = :userId")
    List <RecruitPostEntity> findAllByUserId(Long userId);

    @Query(value =
        "select distinct post from RecruitPostEntity post join fetch post.user left join fetch post.applicants where post in (select a.recruitPost from ApplicantEntity a where a.user.id = :userId)")
    List <RecruitPostEntity> findMyApplyPostsByUserId(Long userId);

    @EntityGraph(value = "RecruitPost.findAll", type = EntityGraphType.LOAD)
    Page <RecruitPostEntity> findAll(Pageable pageable);

    @Query(value = "select rp from RecruitPostEntity rp join rp.user join rp.profile join rp.recruitPostTag as pt left join TagEntity tag on pt.tag.id = tag.id where tag.id = :tagId")
    @EntityGraph(value = "RecruitPost.findAllByTagId", type = EntityGraphType.LOAD)
    Page <RecruitPostEntity> findAllByTagId(Long tagId, Pageable pageable);
}
