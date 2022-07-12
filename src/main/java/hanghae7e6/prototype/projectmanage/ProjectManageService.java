package hanghae7e6.prototype.projectmanage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectManageService {
    private final ProjectManageRepository projectManageRepository;

    //PostMapping
    @Transactional
    public List<ProjectManageEntity> getProject() {
//
        return projectManageRepository.findAll();
    }

    //GetMapping
    public ProjectManageDto getProjectManage(Long projectId){
        ProjectManageEntity projectManageEntity = projectManageRepository.findById(projectId).orElseThrow(
                ()->new NullPointerException("존재하지 않는 프로젝트 관리페이지 입니다.")
        );
        return new ProjectManageDto(projectManageEntity);
    }

    //PutMapping
    public Long update(Long projectId, ProjectManageDto projectManageDto) {
        ProjectManageEntity projectManageEntity = projectManageRepository.findById(projectId).orElseThrow(
                ()->new NullPointerException("수정 실패")
        );
        projectManageEntity.updateByContentDto(projectManageDto);
        return projectId;

        
    }
}
