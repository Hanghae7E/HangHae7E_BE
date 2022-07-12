package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmanage.ProjectManageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name="PROJECT")
public class ProjectEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String projectName;
    private int totalMembers;

    public ProjectEntity(ProjectDto projectDto) {
        this.projectId = projectDto.getProjectId();
        this.projectName = projectDto.getProjectName();
        this.totalMembers = projectDto.getTotalMembers();
    }

    public void update(ProjectDto projectDto) {
        this.projectId = projectDto.getProjectId();
        this.projectName = projectDto.getProjectName();
        this.totalMembers = projectDto.getTotalMembers();
    }

//    public ProjectEntity(Long projectId, ProjectManageDto projectManageDto) {
//    }
}
