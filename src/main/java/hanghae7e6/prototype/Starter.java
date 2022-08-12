package hanghae7e6.prototype;

import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.project.ProjectEntity;
import hanghae7e6.prototype.project.ProjectRepository;
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
import hanghae7e6.prototype.workspace.redis.UserStatusEntity;
import hanghae7e6.prototype.workspace.redis.UserStatusService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Starter {



    UserRepository userRepository;
    UserStatusService userStatusService;
    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectTagsRepository projectTagsRepository;
    ProfileRepository profileRepository;
    PositionRepository positionRepository;
    WorkSpaceRepository workSpaceRepository;


    @Autowired
    public Starter(UserRepository userRepository, UserStatusService userStatusService, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, ProjectTagsRepository projectTagsRepository, ProfileRepository profileRepository, PositionRepository positionRepository, WorkSpaceRepository workSpaceRepository) {
        this.userRepository = userRepository;
        this.userStatusService = userStatusService;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectTagsRepository = projectTagsRepository;
        this.profileRepository = profileRepository;
        this.positionRepository = positionRepository;
        this.workSpaceRepository = workSpaceRepository;
    }


    private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 *1000 * 24;  // 초단위, 24시간




    public void doInit(){
//        UserEntity userB1 = UserEntity.builder()
//                .email("test1@test.com")
//                .username("tester1")
//                .userRole(UserRole.USER)
//                .socialType("google")
//                .build();
//
//        UserEntity userB2 = UserEntity.builder()
//                .email("test2@test.com")
//                .username("tester2")
//                .userRole(UserRole.USER)
//                .socialType("google")
//                .build();
//
//        UserEntity user1 = userRepository.save(userB1);
//        UserEntity user2 = userRepository.save(userB2);
//
//
//        ProfileEntity profileB1 = ProfileEntity.builder()
//                .user(user1)
//                .position(positionRepository.findById(1L).orElseThrow(IllegalArgumentException::new))
//                .build();
//
//        ProfileEntity profileB2 = ProfileEntity.builder()
//                .user(user2)
//                .position(positionRepository.findById(1L).orElseThrow(IllegalArgumentException::new))
//                .build();
//
//        ProfileEntity profile1 = profileRepository.save(profileB1);
//        ProfileEntity profile2 = profileRepository.save(profileB2);


//        ProjectEntity projectB = ProjectEntity.builder()
//                .projectName("test")
//                .uuid("testUuid")
//                .imgUrl("testUrl")
//                .build();
//
//        ProjectEntity project = projectRepository.save(projectB);
//
//
//        ProjectTagsEntity projectTagsB = ProjectTagsEntity.builder()
//                .tag(new TagEntity(1L))
//                .project(project)
//                .build();
//
//        projectTagsRepository.save(projectTagsB);
//
//
//        ProjectMemberEntity projectMemberB = ProjectMemberEntity.builder()
//                .project(project)
//                .user(user1)
//                .build();
//
//        ProjectMemberEntity projectMember = projectMemberRepository.save(projectMemberB);
//
//
//
//
//        WorkSpaceEntity workSpaceB = WorkSpaceEntity.builder()
//                .title("test")
//                .content("test")
//                .project(project)
//                .build();
//
//        WorkSpaceEntity workSpace = workSpaceRepository.save(workSpaceB);
        UserEntity user1 = userRepository.findById(7L).orElseThrow(IllegalArgumentException::new);
        UserEntity user2 = userRepository.findById(8L).orElseThrow(IllegalArgumentException::new);

        System.out.println("test1 - "+createJwt(user1));
        System.out.println("test2 - "+createJwt(user2));


    }

    public String createJwt(UserEntity user){

        String secretKey = Base64.getEncoder().encodeToString("test".getBytes());
        Date now = new Date();

        String userId = user.getId().toString();
        String email = user.getEmail();
        String socialType = user.getSocialType();

        Claims claims = Jwts.claims();

        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("social-type", socialType);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }

    public void redisTest(){
        String test_uuid = "test_uuid";
        UserStatusEntity userStatus1 = userStatusService.setMemberStatus(test_uuid, 1L, -1L);
        UserStatusEntity userStatus2 = userStatusService.setMemberStatus(test_uuid, 2L, -1L);
        System.out.println(userStatus1.getUuid() + "" + userStatus1.getUserId());
        System.out.println(userStatus2.getUuid() + "" + userStatus2.getUserId());

        List<UserStatusEntity> userStatusEntities1 = userStatusService.findAllMemberByUuid(test_uuid);
        userStatusEntities1.stream().forEach(System.out::println);
        System.out.println("");


        userStatusService.deleteStatus(1L, test_uuid);

        List<UserStatusEntity> userStatusEntities2 = userStatusService.findAllMemberByUuid(test_uuid);
        userStatusEntities2.stream().forEach(System.out::println);
    }


}
