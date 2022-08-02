package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmember.ProjectMemberDto;
import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsDto;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import hanghae7e6.prototype.workspace.dto.SimpleWorkSpaceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {

  private String projectName;
  private String imgUrl;
  private String uuid;
  private List<ProjectTagsDto> tags;
  private List<ProjectMemberDto> team;
  private List<SimpleWorkSpaceDto> workSpaces;

  public ProjectResponseDto(ProjectEntity project){
    List<ProjectMemberEntity> projectMembers = project.getProjectMembers();
    List<ProjectTagsEntity> projectTags = project.getProjectTags();

    this.projectName = project.getProjectName();
    this.imgUrl = project.getImgUrl();
    this.uuid = project.getUuid();
    this.team = projectMembers.stream().map(ProjectMemberDto::new).collect(Collectors.toList());
    this.tags = projectTags.stream().map(ProjectTagsDto::new).collect(Collectors.toList());
  }

}
