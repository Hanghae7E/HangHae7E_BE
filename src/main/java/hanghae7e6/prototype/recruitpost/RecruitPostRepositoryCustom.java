package hanghae7e6.prototype.recruitpost;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.recruitposttag.QRecruitPostTagEntity;
import hanghae7e6.prototype.tag.QTagEntity;
import hanghae7e6.prototype.tag.TagValue;
import hanghae7e6.prototype.user.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RecruitPostRepositoryCustom {
    private JPAQueryFactory queryFactory;
    private QRecruitPostEntity post = QRecruitPostEntity.recruitPostEntity;
    private QUserEntity user = QUserEntity.userEntity;
    private QRecruitPostTagEntity postTag = QRecruitPostTagEntity.recruitPostTagEntity;
    private QTagEntity tag = QTagEntity.tagEntity;


    public RecruitPostRepositoryCustom(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    public List<SimplePostResponseDto> findAllByTagId(PostParamDto dto){
        JPAQuery<SimplePostResponseDto> query =
                queryFactory.select(
                Projections.fields(SimplePostResponseDto.class,
                        post.id.as("postId"),
                        user.username,
                        post.title,
                        post.projectStartTime,
                        post.projectEndTime,
                        post.recruitDueTime
                ))
                .from(post)
                .innerJoin(post.recruitPostTag, postTag);

        if(TagValue.notAll(dto.getTagId())){
            query.on(postTag.tag.id.eq(dto.getTagId()));
        }

        return query
                .innerJoin(post.user, user)
                .orderBy(SortValue.getOrderSpecifier(dto.getSort()))
                .offset(dto.getOffSet())
                .limit(dto.getLimit())
                .fetch();
    }


    public DetailPostResponseDto findById(Long postId){
        return Optional.ofNullable(
                queryFactory.select(
                        Projections.fields(DetailPostResponseDto.class,
                                post.id.as("postId"),
                                post.user.id.as("userId"),
                                post.title))
                        .from(post)
                        .where(post.id.eq(postId))
                        .fetchOne())
                        .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
    }


    public RecruitPostEntity findByIdAndUserId(Long postId, Long userId){
        return Optional.ofNullable(queryFactory.select(post)
                .from(post)
                .where(post.id.eq(postId),
                        post.user.id.eq(userId))
                .fetchOne())
                .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
    }
}

