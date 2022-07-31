package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProjectRequestDto {

    private Long recruitPostId;
    private List<Long> tagIds;
    private List<Long> userIds;
    private String title;
    private String imgUrl;


    public List<ProjectTagsEntity> getProjectTags(){
        return tagIds.stream()
                .map(ProjectTagsEntity::getEntity)
                .collect(Collectors.toList());
    }


    public List<ProjectMemberEntity> getProjectMember(){
        return userIds.stream()
                .map(ProjectMemberEntity::getEntity)
                .collect(Collectors.toList());
    }


    public ProjectEntity toEntity(){
        return ProjectEntity.builder()
                .projectName(title)
                .imgUrl(imgUrl)
                .projectTags(new ArrayList<>())
                .projectMembers(new ArrayList<>())
                .build();
    }


}
