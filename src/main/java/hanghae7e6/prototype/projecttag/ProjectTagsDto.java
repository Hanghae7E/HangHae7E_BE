package hanghae7e6.prototype.projecttag;

import hanghae7e6.prototype.tag.TagEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectTagsDto {

  private Long tagId;
  private String tagName;

  public ProjectTagsDto(ProjectTagsEntity projectTags){
    TagEntity tag = projectTags.getTag();
    this.tagId = tag.getId();
    this.tagName = tag.getBody();
  }
}
