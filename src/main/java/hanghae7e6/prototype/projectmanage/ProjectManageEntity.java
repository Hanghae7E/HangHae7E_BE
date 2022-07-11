package hanghae7e6.prototype.projectmanage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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


//    @Column(name = "RECRUIT_POST_ID",nullable = false)
//    private Long recruitPostId;
//
//    @Column(name = "USER_ID",nullable = false, unique = true)
//    private Long userId;
//
//    @Column(name = "TEAM_ID",nullable = false)
//    private Long teamId;

    @Column(name = "TEAM_ID",nullable = false)
    private Long teamId;

    @Column(name = "PROJECT_ID",nullable = false)
    private Long projectId;

    @Column(name = "WORKSPACE_ID",nullable = false)
    private Long workspaceId;


}
