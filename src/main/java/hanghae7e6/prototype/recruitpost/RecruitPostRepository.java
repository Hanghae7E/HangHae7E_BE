package hanghae7e6.prototype.recruitpost;

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
}
