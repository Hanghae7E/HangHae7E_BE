package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmember.ProjectMemberDto;
import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsDto;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProjectResponseDto {

    private Long projectId;
    private String projectName;
    private String imgUrl;
    private LocalDateTime createdAt;
    private List<ProjectTagsDto> tags;

    public SimpleProjectResponseDto(ProjectEntity project){
        List<ProjectTagsEntity> projectTags = project.getProjectTags();

        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();
        this.imgUrl = project.getImgUrl();
        this.tags = projectTags.stream().map(ProjectTagsDto::new).collect(Collectors.toList());
    }

    public static List<SimpleProjectResponseDto> toDto(Page<ProjectEntity> projects){
        return projects.stream()
                .map(SimpleProjectResponseDto::new)
                .collect(Collectors.toList());
    }
}
