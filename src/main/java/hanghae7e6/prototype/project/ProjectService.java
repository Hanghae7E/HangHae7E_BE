package hanghae7e6.prototype.project;

import hanghae7e6.prototype.projecttag.ProjectTagsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {


  private ProjectRepository projectRepository;


  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }


  public ProjectEntity createProject(ProjectRequestDto requestDto) {

    ProjectEntity project = requestDto.toEntity();
    projectRepository.save(project);

    return project;
  }


  public List<ProjectEntity> getProject(ProjectTagsEntity projectTagsEntity) {
    return projectRepository.findAll();
  }


//  @Transactional
//  public Long updateProject(Long projectId,ProjectDto projectDto) {
//    ProjectEntity project = projectRepository.findById(projectId).orElseThrow(
//            ()-> new NullPointerException("존재하지 않는 프로젝트입니다.")
//    );
//    project.updateProject(projectDto);
//    return project.getProjectId();
//  }


  public Long deleteProject(Long projectId) {
    projectRepository.deleteById(projectId);
    return projectId;
  }
}
