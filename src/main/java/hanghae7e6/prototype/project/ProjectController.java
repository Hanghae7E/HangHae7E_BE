package hanghae7e6.prototype.project;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


//  @GetMapping("/projects")
//  public List<ProjectEntity> getProject(@RequestBody ProjectTagsEntity projectTagsEntity) {
//    return projectService.getProject(projectTagsEntity);
//  }



  @PostMapping("/teamproject")
  public ProjectEntity createProject(
          @RequestBody ProjectRequestDto requestDto) {

    return projectService.createProject(requestDto);
  }




//  @PutMapping("/projects/{projectId}")
//  public Long updateProject(@PathVariable Long projectId,
//                            @RequestBody ProjectDto projectDto) {
//    projectService.updateProject(projectId,projectDto);
//
//    return projectId;
//  }


  @DeleteMapping("/projects/{projectId}")
  public Long deleteProject(@PathVariable Long projectId) {
    return projectService.deleteProject(projectId);
  }
}
