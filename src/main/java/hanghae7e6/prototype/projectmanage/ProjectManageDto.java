package hanghae7e6.prototype.projectmanage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectManageDto {
    private Long projectManageId;
    private Long teamId;
    private Long projectId;
    private Long workspaceId;
    private String projectName;
    private int totalMembers;


    public ProjectManageDto(ProjectManageEntity projectManageEntity) {
        this.projectName = projectManageEntity.getProjectName();
        this.totalMembers = projectManageEntity.getTotalMembers();
    }
}
