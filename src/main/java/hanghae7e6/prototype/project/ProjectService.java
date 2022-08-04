package hanghae7e6.prototype.project;

import hanghae7e6.prototype.common.BaseTimeSort;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.user.CustomUserDetails;
import hanghae7e6.prototype.workspace.WorkSpaceEntity;
import hanghae7e6.prototype.workspace.WorkSpaceRepository;
import hanghae7e6.prototype.workspace.WorkSpaceService;
import hanghae7e6.prototype.workspace.dto.SimpleWorkSpaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectService {


  private ProjectRepository projectRepository;
  final Integer SIZE = 10;
  final Sort SORT = BaseTimeSort.LATEST_DATA.getSort();


  public ProjectService(ProjectRepository projectRepository){
    this.projectRepository = projectRepository;
  }


  @Transactional
  public ProjectEntity createProject(ProjectRequestDto requestDto) {
    ProjectEntity projectObj = requestDto.toEntity();
    ProjectEntity project = projectRepository.save(projectObj);
    project.addProjectTags(requestDto.getProjectTags());

    return project;
  }


  @Transactional(readOnly = true)
  public DetailProjectResponseDto getProject(Long projectId) {

    ProjectEntity project = projectRepository.detailJoinById(projectId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));

    DetailProjectResponseDto responseDto = new DetailProjectResponseDto(project);
    return responseDto;
  }


  @Transactional(readOnly = true)
  public Map<String, Object> getProjects(CustomUserDetails userDetails, Integer page){

    Map<String, Object> responseMap = new HashMap<>();

    Pageable pageable = PageRequest.of(page, SIZE, SORT);
    Page<ProjectEntity> projects = projectRepository
            .simpleJoinByUserId(userDetails.getId(), pageable);

    List<SimpleProjectResponseDto> responseDto = SimpleProjectResponseDto.toDto(projects);

    responseMap.put("isLast", projects.isLast());
    responseMap.put("projects", responseDto);

    return responseMap;
  }


//  @Transactional
//  public Long updateProject(Long projectId,ProjectDto projectDto) {
//    ProjectEntity project = projectRepository.findById(projectId).orElseThrow(
//            ()-> new NullPointerException("존재하지 않는 프로젝트입니다.")
//    );
//    project.updateProject(projectDto);
//    return project.getProjectId();
//  }


  public void deleteProject(Long projectId) {
    projectRepository.deleteById(projectId);
  }
}
