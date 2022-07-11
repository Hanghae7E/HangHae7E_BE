package hanghae7e6.prototype.recruitposttag;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanghae7e6.prototype.recruitpost.QRecruitPostEntity;
import hanghae7e6.prototype.tag.QTagEntity;
import hanghae7e6.prototype.user.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecruitPostTagRepositoryCustom {
    private JPAQueryFactory queryFactory;
    private QRecruitPostEntity post = QRecruitPostEntity.recruitPostEntity;
    private QUserEntity user = QUserEntity.userEntity;
    private QRecruitPostTagEntity postTag = QRecruitPostTagEntity.recruitPostTagEntity;
    private QTagEntity tag = QTagEntity.tagEntity;

    public RecruitPostTagRepositoryCustom(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    public List<RecruitPostTagDto> findByPostIds(List<Long> postIds){
        return  queryFactory.select(
                Projections.fields(RecruitPostTagDto.class,
                        postTag.recruitPost.id.as("postId"),
                        postTag.tag.id.as("tagId")))
                .from(postTag)
                .where(postTag.recruitPost.id.in(postIds))
                .fetch();
    }


    public List<Long> findByPostId(Long postIds){
        return  queryFactory.select(postTag.tag.id)
                .from(postTag)
                .where(postTag.recruitPost.id.eq(postIds))
                .fetch();
    }
}
