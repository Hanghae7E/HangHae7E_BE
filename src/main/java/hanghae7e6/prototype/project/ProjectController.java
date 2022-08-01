package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


  @GetMapping("/project/{projectId}")
  public ResponseEntity<ProjectResponseDto> getProject(
          @PathVariable Long projectId) {
    ProjectResponseDto responseDto = projectService.getProject(projectId);

    return ResponseEntity.ok().body(responseDto);
  }



  @PostMapping("/project")
  public void createProject(
          @RequestBody ProjectRequestDto requestDto) {
        projectService.createProject(requestDto);
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
