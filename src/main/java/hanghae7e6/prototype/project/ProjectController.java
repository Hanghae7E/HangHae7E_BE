package hanghae7e6.prototype.project;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project/{projectId}")
    public Long createProject(@PathVariable Long projectId, @RequestBody ProjectDto projectDto){
        ProjectEntity projectEntity = new ProjectEntity(projectDto);
        projectService.createProject(projectEntity);
        return projectId;
    }

    @GetMapping("/project/{projectId}")
    public Long getProject(@PathVariable Long projectId){
        projectService.getProject(projectId);
        return projectId;
    }

    @PutMapping("/project/{projectId}")
    public Long updateProject(@PathVariable Long projectId, @RequestBody ProjectDto projectDto){
        projectService.updateProject(projectId, projectDto);
        return projectId;
    }



}


