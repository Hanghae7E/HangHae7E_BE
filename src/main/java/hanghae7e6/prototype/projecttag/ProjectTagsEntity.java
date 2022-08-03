package hanghae7e6.prototype.projecttag;

import hanghae7e6.prototype.project.ProjectEntity;
import hanghae7e6.prototype.tag.TagEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@Table(name = "PROJECT_TAGS")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTagsEntity {


  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long projectTagsId;


  @ManyToOne
  @JoinColumn(name = "PROJECT_ID")
  private ProjectEntity project;

  @ManyToOne
  @JoinColumn(name = "TAG_ID")
  private TagEntity tag;


  public ProjectTagsEntity(Long projectTagsId) {
    this.projectTagsId = projectTagsId;
  }

  public static ProjectTagsEntity getEntity(Long tagId){
     return ProjectTagsEntity.builder()
             .tag(new TagEntity(tagId))
             .build();
  }


}
