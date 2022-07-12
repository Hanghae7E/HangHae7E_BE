package hanghae7e6.prototype.projectmanage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="PROJECT_MANAGEMENT")
//@DynamicInsert
//@DynamicUpdate
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,property = "id")
public class ProjectManageEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectManagementId;

    @Column(name = "TEAM_ID",nullable = false)
    private Long teamId;

    @Column(name = "PROJECT_ID",nullable = false)
    private Long projectId;

//    @Column(name = "WORKSPACE_ID",nullable = false)
//    private Long workspaceId;

    @Column(name = "PROJECT_NAME",nullable = false)
    private String projectName;

    @Column(name = "TOTAL_MEMBERS",nullable = false)
    private int totalMembers;

    public ProjectManageEntity(ProjectManageDto projectManageDto){
        this.projectManagementId = projectManageDto.getProjectManageId();
        this.projectId = projectManageDto.getProjectId();
        this.teamId = projectManageDto.getTeamId();
        this.projectName = projectManageDto.getProjectName();
        this.totalMembers = projectManageDto.getTotalMembers();
    }

    public void updateByContentDto(ProjectManageDto projectManageDto) {
        this.projectName = projectManageDto.getProjectName();
        this.totalMembers = projectManageDto.getTotalMembers();
    }
}


//    @Column(name = "RECRUIT_POST_ID",nullable = false)
//    private Long recruitPostId;
//
//    @Column(name = "USER_ID",nullable = false, unique = true)
//    private Long userId;
//
//    @Column(name = "TEAM_ID",nullable = false)
//    private Long teamId;