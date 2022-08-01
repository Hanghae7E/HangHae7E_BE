//package hanghae7e6.prototype.project;
//
//import com.querydsl.core.types.ExpressionUtils;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.JPAExpressions;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import hanghae7e6.prototype.exception.ErrorCode;
//import hanghae7e6.prototype.exception.NotFoundException;
//import hanghae7e6.prototype.profile.entity.QPositionEntity;
//import hanghae7e6.prototype.profile.entity.QProfileEntity;
//import hanghae7e6.prototype.projectmember.ProjectMemberDto;
//import hanghae7e6.prototype.projectmember.QProjectMemberEntity;
//import hanghae7e6.prototype.projecttag.ProjectTagsDto;
//import hanghae7e6.prototype.projecttag.QProjectTagsEntity;
//import hanghae7e6.prototype.recruitpost.QRecruitPostEntity;
//import hanghae7e6.prototype.recruitpost.SortValue;
//import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
//import hanghae7e6.prototype.recruitposttag.QRecruitPostTagEntity;
//import hanghae7e6.prototype.recruitposttag.RecruitPostTagRepositoryCustom;
//import hanghae7e6.prototype.tag.QTagEntity;
//import hanghae7e6.prototype.user.QUserEntity;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Repository
//public class ProjectControllerCustom {
//    private JPAQueryFactory queryFactory;
//    private QRecruitPostEntity post = QRecruitPostEntity.recruitPostEntity;
//    private QUserEntity user = QUserEntity.userEntity;
//    private QTagEntity tag = QTagEntity.tagEntity;
//    private QProfileEntity profile = QProfileEntity.profileEntity;
//    private QPositionEntity position = QPositionEntity.positionEntity;
//    private QProjectEntity project = QProjectEntity.projectEntity;
//    private QProjectMemberEntity projectMember = QProjectMemberEntity.projectMemberEntity;
//    private QProjectTagsEntity projectTags = QProjectTagsEntity.projectTagsEntity;
//
//    private RecruitPostTagRepositoryCustom recruitPostTagRepositoryCustom;
//
//    public ProjectControllerCustom(
//            JPAQueryFactory queryFactory,
//            RecruitPostTagRepositoryCustom recruitPostTagRepositoryCustom) {
//        this.queryFactory = queryFactory;
//        this.recruitPostTagRepositoryCustom = recruitPostTagRepositoryCustom;
//    }
//
//    public ProjectResponseDto findAndJoinById(Long projectId){
//
//
//
//
//        ProjectResponseDto projectDto = Optional.ofNullable(queryFactory.select(
//                        Projections.fields(ProjectResponseDto.class,
//                                project.projectName,
//                                project.imgUrl,
//                                ExpressionUtils.as(JPAExpressions
//                                        .select(Projections.fields(ProjectTagsDto.class,
//                                                ))
//                                        .from(projectMember), "project")))
//                .from(project)
//                .leftJoin(project.projectMembers, projectMember)
//                .innerJoin(projectMember.user, user)
//                .leftJoin(project.projectTags, projectTags)
//                .innerJoin(projectTags.tag, tag)
//                .where(project.projectId.eq(projectId))
//                .fetchOne())
//                .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
//
//    }
//}
//
//
