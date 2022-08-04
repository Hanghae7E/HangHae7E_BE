package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "TEAM_PROJECT")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column
    private String projectName;

    @Column(unique = true)
    private String uuid;

    @Column
    private String imgUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    private List<ProjectTagsEntity> projectTags = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    private List<ProjectMemberEntity> projectMembers = new ArrayList<>();


    public ProjectEntity(Long projectId) {
        this.projectId = projectId;
    }


    public void addProjectTags(List<ProjectTagsEntity> entities){
        entities.stream()
                .forEach(entity -> {
                    projectTags.add(entity);
                    entity.setProject(this);
                });
    }

    public void addProjectMembers(List<ProjectMemberEntity> entities){
        entities.stream()
                .forEach(entity -> {
                    projectMembers.add(entity);
                    entity.setProject(this);
                });
    }
}