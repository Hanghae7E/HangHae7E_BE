package hanghae7e6.prototype.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    //PostMapping
    @Transactional
    public ProjectEntity createProject(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    //GetMapping
    @Transactional
    public ProjectEntity getProject(Long projectId){
        return projectRepository.findAll(projectId);

    }

    //PutMapping
    public Long updateProject(Long projectId, ProjectDto projectDto) {
        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                ()-> new NullPointerException("수정한 내용이 없습니다.")
        );
        projectEntity.update(projectDto);
        return projectId;

    }
}
