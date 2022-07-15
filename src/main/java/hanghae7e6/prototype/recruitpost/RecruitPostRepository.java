package hanghae7e6.prototype.recruitpost;

import com.querydsl.core.types.Projections;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import javax.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

//    @Query(value = "select post from RecruitPostEntity post join fetch post.user join fetch ProfileEntity profile on post.user.id = profile.user.id")
    @EntityGraph(value = "RecruitPost.findAll", type = EntityGraphType.FETCH)
    Page <RecruitPostEntity> findAll(Pageable pageable);

//    @Query(value = "select post from RecruitPostEntity post join fetch post.user join fetch ProfileEntity profile on post.user.id = profile.user.id join fetch RecruitPostTagEntity rt on rt.recruitPost.id =v post.id where rt.tag.id = :tagId")
//    Page <RecruitPostEntity> findByTagId(Long tagId, Pageable pageable);
//    @EntityGraph(attributePaths = {"user", "profile", "recruitPostTag", "recruitPostTag.tag"}, type = EntityGraphType.FETCH)
//    Page<RecruitPostEntity> findAllByTagId(Long tagId, Pageable pageable);
}
//    List<SimplePostResponseDto> postDtos = queryFactory.select(
//                                                           Projections.fields(SimplePostResponseDto.class,
//                                                               post.id.as("postId"),
//                                                               user.username,
////                        position.positionName.as("userPosition"),
//                                                               profile.imageUrl.as("authorImage"),
//                                                               post.title,
//                                                               post.projectStartTime,
//                                                               post.projectEndTime,
//                                                               post.recruitDueTime,
//                                                               post.imageUrl.as("projectImage"),
//                                                               post.requiredDesigners,
//                                                               post.requiredDevelopers,
//                                                               post.requiredProjectManagers
//
//                                                                             ))
//                                                       .from(post)
//                                                       .innerJoin(post.user, user)
//                                                       .innerJoin(profile)
//                                                       .on(profile.user.id.eq(user.id))
////                .innerJoin(profile.position, position)
//                                                       .where(post.id.in(postIds))
//                                                       .orderBy(SortValue.getOrderSpecifier(dto.getSort()))
//                                                       .fetch();

