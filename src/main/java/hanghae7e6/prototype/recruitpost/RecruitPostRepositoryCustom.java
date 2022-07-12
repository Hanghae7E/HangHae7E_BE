package hanghae7e6.prototype.recruitpost;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.profile.entity.QPositionEntity;
import hanghae7e6.prototype.profile.entity.QProfileEntity;
import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.recruitposttag.QRecruitPostTagEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagDto;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagRepositoryCustom;
import hanghae7e6.prototype.tag.QTagEntity;
import hanghae7e6.prototype.tag.TagValue;
import hanghae7e6.prototype.user.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RecruitPostRepositoryCustom {
    private JPAQueryFactory queryFactory;
    private QRecruitPostEntity post = QRecruitPostEntity.recruitPostEntity;
    private QUserEntity user = QUserEntity.userEntity;
    private QRecruitPostTagEntity postTag = QRecruitPostTagEntity.recruitPostTagEntity;
    private QTagEntity tag = QTagEntity.tagEntity;
    private QProfileEntity profile = QProfileEntity.profileEntity;
    private QPositionEntity position = QPositionEntity.positionEntity;

    private RecruitPostTagRepositoryCustom recruitPostTagRepositoryCustom;

    public RecruitPostRepositoryCustom(
            JPAQueryFactory queryFactory,
            RecruitPostTagRepositoryCustom recruitPostTagRepositoryCustom){
        this.queryFactory = queryFactory;
        this.recruitPostTagRepositoryCustom = recruitPostTagRepositoryCustom;
    }


    public List<SimplePostResponseDto> findAllByTagId(PostParamDto dto){


        Map<Long, SimplePostResponseDto> dtoMap = new HashMap<>();

        List<Long> postIds = getPostIds(dto);
        List<RecruitPostTagDto> postTagDtos =
                recruitPostTagRepositoryCustom.findByPostIds(postIds);

        List<SimplePostResponseDto> postDtos = queryFactory.select(
                Projections.fields(SimplePostResponseDto.class,
                        post.id.as("postId"),
                        user.username,
//                        position.positionName.as("userPosition"),
                        profile.imageUrl.as("authorImage"),
                        post.title,
                        post.projectStartTime,
                        post.projectEndTime,
                        post.recruitDueTime
                ))
                .from(post)
                .innerJoin(post.user, user)
                .innerJoin(profile)
                .on(profile.user.id.eq(user.id))
//                .innerJoin(profile.position, position)
                .where(post.id.in(postIds))
                .orderBy(SortValue.getOrderSpecifier(dto.getSort()))
                .fetch();


        postDtos.forEach((post) -> dtoMap.put(post.getPostId(), post));

        postTagDtos.forEach((postTag) -> {
            SimplePostResponseDto postDto = dtoMap.get(postTag.getPostId());
            postDto.addTag(postTag.getTagId());
            dtoMap.put(postTag.getPostId(), postDto);
        });

        return new ArrayList<>(dtoMap.values());

    }

    public List<Long> getPostIds(PostParamDto dto){
        JPAQuery<Long> query = queryFactory
                .select(post.id)
                .from(post);

        if(TagValue.notAll(dto.getTagId())){
            query.join(post.recruitPostTag, postTag)
                    .on(postTag.tag.id.eq(dto.getTagId()));
        }

        return query.offset(dto.getOffSet())
                .limit(dto.getLimit())
                .orderBy(SortValue.getOrderSpecifier(dto.getSort()))
                .fetch();
    }


    public DetailPostResponseDto findById(Long postId){
        DetailPostResponseDto dto = Optional.ofNullable(
                queryFactory.select(
                        Projections.fields(DetailPostResponseDto.class,
                                post.id.as("postId"),
                                post.user.id.as("userId"),
                                post.title,
                                post.body,
                                post.projectStartTime,
                                post.projectEndTime,
                                post.recruitDueTime,
                                post.totalMemberCount
                        ))
                        .from(post)
                        .where(post.id.eq(postId))
                        .fetchOne())
                        .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));


        List<Long> postTagDtos =
                recruitPostTagRepositoryCustom.findByPostId(dto.getPostId());

        dto.setTags(postTagDtos);

        return dto;
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

