package hanghae7e6.prototype.projectmanage;

import hanghae7e6.prototype.project.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectManageController {
    private final ProjectManageService projectManageService;
    private final ProjectManageRepository projectManageRepository;

    @PostMapping("/projectmanagement/{projectId}")
    public Long createProjectManagement(@PathVariable Long projectId, @RequestBody ProjectManageDto projectManageDto){
//        projectManageService
        ProjectManageEntity projectManageEntity = new ProjectManageEntity(projectManageDto);
        projectManageRepository.save(projectManageEntity);
        return projectId;
    }

    @GetMapping("/projectmanagement/{projectId}")
    public ProjectManageDto getProjectManage(@PathVariable Long projectId){
        return projectManageService.getProjectManage(projectId);
//        projectManageRepository.findById(getProject());
    }

    @PutMapping("/projectmanagement/{projectId}")
    public Long updateProject(@PathVariable Long projectId, @RequestBody ProjectManageDto projectManageDto) {
        return projectManageService.update(projectId,projectManageDto);

    }





}
