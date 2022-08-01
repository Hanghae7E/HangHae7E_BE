package hanghae7e6.prototype;

import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.profile.repository.ProfileTagRepository;
import hanghae7e6.prototype.project.ProjectEntity;
import hanghae7e6.prototype.project.ProjectRepository;
import hanghae7e6.prototype.projectmember.ProjectMemberDto;
import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projectmember.ProjectMemberRepository;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsRepository;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserRepository;
import hanghae7e6.prototype.user.UserRole;
import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import hanghae7e6.prototype.workspace.WorkSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class Starter {

    UserRepository userRepository;
    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectTagsRepository projectTagsRepository;
    ProfileRepository profileRepository;
    PositionRepository positionRepository;
    WorkSpaceRepository workSpaceRepository;


    @Autowired
    public Starter(UserRepository userRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, ProjectTagsRepository projectTagsRepository, ProfileRepository profileRepository, PositionRepository positionRepository, WorkSpaceRepository workSpaceRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectTagsRepository = projectTagsRepository;
        this.profileRepository = profileRepository;
        this.positionRepository = positionRepository;
        this.workSpaceRepository = workSpaceRepository;
    }


    public void doInit(){
        UserEntity userB = UserEntity.builder()
                .email("test@test.com")
                .username("tester")
                .userRole(UserRole.USER)
                .socialType("testType")
                .build();

        UserEntity user = userRepository.save(userB);


        ProjectEntity projectB = ProjectEntity.builder()
                .projectName("test")
                .uuid("testUuid")
                .imgUrl("testUrl")
                .build();

        ProjectEntity project = projectRepository.save(projectB);


        ProjectTagsEntity projectTagsB = ProjectTagsEntity.builder()
                .tag(new TagEntity(1L))
                .project(project)
                .build();

        projectTagsRepository.save(projectTagsB);


        ProjectMemberEntity projectMemberB = ProjectMemberEntity.builder()
                .project(project)
                .user(user)
                .build();

        ProjectMemberEntity projectMember = projectMemberRepository.save(projectMemberB);


        ProfileEntity profileB = ProfileEntity.builder()
                .user(user)
                .position(positionRepository.findById(1L).orElseThrow(IllegalArgumentException::new))
                .build();

        ProfileEntity profile = profileRepository.save(profileB);


        WorkSpaceEntity workSpaceB = WorkSpaceEntity.builder()
                .title("test")
                .content("test")
                .project(project)
                .build();

        WorkSpaceEntity workSpace = workSpaceRepository.save(workSpaceB);

    }

}
