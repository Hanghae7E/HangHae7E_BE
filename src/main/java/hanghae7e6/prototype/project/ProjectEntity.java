package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmanage.ProjectManageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name="PROJECT")
public class ProjectEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String projectName;
    private int totalMembmers;

    public ProjectEntity(Long projectId, ProjectManageDto projectManageDto) {

    }

//    public ProjectEntity(Long projectId, ProjectManageDto projectManageDto) {
//    }
}
