package hanghae7e6.prototype.project;

import hanghae7e6.prototype.user.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


  @GetMapping("/project/{projectId}")
  public ResponseEntity<DetailProjectResponseDto> getProject(
          @PathVariable Long projectId) {
    DetailProjectResponseDto responseDto = projectService.getProject(projectId);

    return ResponseEntity.ok().body(responseDto);
  }

    @GetMapping("/project")
    public ResponseEntity<Map<String, Object>> getProjects(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Nullable @RequestParam("page") Integer page) {

        page = Objects.isNull(page) || (1 <= page)? 0 : page - 1 ;

        Map<String, Object> responseMap= projectService.getProjects(userDetails, page);
        return ResponseEntity.ok().body(responseMap);
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
    projectService.deleteProject(projectId);
    return projectId;
  }
}
