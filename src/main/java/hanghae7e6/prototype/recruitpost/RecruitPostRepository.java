package hanghae7e6.prototype.recruitpost;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
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

    @EntityGraph(value = "RecruitPost.FetchAttributes", type = EntityGraphType.LOAD)
    Page <RecruitPostEntity> findAllByRecruitStatusAndRecruitDueTimeGreaterThan(Boolean recruitStatus,
        LocalDate recruitDueTime, Pageable pageable);

    @Query(value = "select rp from RecruitPostEntity rp join rp.user join rp.profile join rp.recruitPostTag as pt left join TagEntity tag on pt.tag.id = tag.id where rp.recruitStatus = :recruitStatus and rp.recruitDueTime > :recruitDueTime and tag.id = :tagId")
    @EntityGraph(value = "RecruitPost.FetchWithTag", type = EntityGraphType.LOAD)
    Page <RecruitPostEntity> findAllByTagIdAndRecruitStatusAndRecruitDueTimeGreaterThan(Long tagId,  Boolean recruitStatus, LocalDate recruitDueTime, Pageable pageable);

    @EntityGraph(value = "RecruitPost.FetchWithTag", type = EntityGraphType.LOAD)
    Optional<RecruitPostEntity> findById(Long recruitPostId);
}
