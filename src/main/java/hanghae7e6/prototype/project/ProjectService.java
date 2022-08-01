package hanghae7e6.prototype.project;

import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.workspace.WorkSpaceService;
import hanghae7e6.prototype.workspace.dto.SimpleWorkSpaceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {


  private ProjectRepository projectRepository;
  private WorkSpaceService workSpaceService;
  private ProfileRepository profileRepository;



  public ProjectService(ProjectRepository projectRepository,
                        WorkSpaceService workSpaceService,
                        ProfileRepository profileRepository) {
    this.workSpaceService = workSpaceService;
    this.projectRepository = projectRepository;
    this.profileRepository = profileRepository;
  }


  @Transactional
  public ProjectEntity createProject(ProjectRequestDto requestDto) {
    ProjectEntity projectObj = requestDto.toEntity();

    ProjectEntity project = projectRepository.save(projectObj);

    project.addProjectTags(requestDto.getProjectTags());

    return project;
  }


  @Transactional(readOnly = true)
  public ProjectResponseDto getProject(Long projectId) {

    List<SimpleWorkSpaceDto> workSpaceDtos = workSpaceService.getSimpWorkSpacesDto(projectId, 10);

    ProjectEntity project = projectRepository.joinById(projectId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));

    ProjectResponseDto responseDto = new ProjectResponseDto(project);

    responseDto.setWorkSpaces(workSpaceDtos);

    return responseDto;
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
