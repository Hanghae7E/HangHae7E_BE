package hanghae7e6.prototype.projectmember;

import hanghae7e6.prototype.project.ProjectEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "PROJECT_MEMBER")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberEntity {

    @Id
    @Column(nullable = false, name = "PROJECT_MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    public static ProjectMemberEntity getEntity(Long userId){
        return ProjectMemberEntity.builder()
                .user(UserEntity.builder().id(userId).build())
                .build();
    }

}
