package hanghae7e6.prototype.projecttag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectTagsController {

  private final ProjectTagsService projectTagsService;
  private final ProjectTagsRepository projectTagsRepository;

//  @PostMapping("/projectTags")
//  public ProjectTagsEntity createProjectTags(@RequestBody ProjectTagsDto projectTagsDto) {
//    ProjectTagsEntity projectTags = new ProjectTagsEntity(projectTagsDto);
//    return projectTagsRepository.save(projectTags);
//  }

  @GetMapping("/projectTags")
  public List<ProjectTagsEntity> getProjectTags() {
    return projectTagsService.getProjectTags();
  }
}
