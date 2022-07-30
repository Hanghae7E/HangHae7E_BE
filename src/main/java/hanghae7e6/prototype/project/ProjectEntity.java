package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
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

  @Column
  private String imgUrl;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
  @BatchSize(size = 100)
  private List<ProjectTagsEntity> projectTags;


  @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
  @BatchSize(size = 100)
  private List<ProjectMemberEntity> projectMembers;




  public ProjectEntity(ProjectDto projectDto) {
    this.projectName = projectDto.getProjectName();
    this.imgUrl = projectDto.getImgUrl();
//    this.projectTags = projectDto.getProjectTags();
  }

  public ProjectEntity(Long projectId) {
    this.projectId = projectId;
  }
}
