package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.dto.DetailWorkSpaceDto;
import hanghae7e6.prototype.workspace.dto.SimpleWorkSpaceDto;
import hanghae7e6.prototype.workspace.tempprojects.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkSpaceService {

    private WorkSpaceRepository workSpaceRepository;
    final Integer SIZE = 10;
    final Sort SORT = WorkSpaceSort.LATEST_WORKSPACE.getSort();

    @Autowired
    public WorkSpaceService(WorkSpaceRepository workSpaceRepository) {
        this.workSpaceRepository = workSpaceRepository;
    }


    @Transactional
    public WorkSpaceEntity createWorkSpace(Long projectId){
        WorkSpaceEntity workSpace = new WorkSpaceEntity(new ProjectEntity(projectId));
        return workSpaceRepository.save(workSpace);
    }


    @Transactional(readOnly = true)
    public WorkSpaceEntity getWorkSpace(Long projectId, Long workSpaceId){
       return workSpaceRepository.findByProjectAndId(new ProjectEntity(projectId), workSpaceId)
               .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getWorkSpaces(Long projectId, Integer page){

        Map<String, Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(page, SIZE, SORT);
        Page<WorkSpaceEntity> workSpaces = workSpaceRepository.findAllByProject(new ProjectEntity(projectId), pageable);

        List<SimpleWorkSpaceDto> responseDto = SimpleWorkSpaceDto.toDto(workSpaces);

        responseMap.put("isLast", workSpaces.isLast());
        responseMap.put("wordSpaces", responseDto);

        return responseMap;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> searchWorkSpaces(Long projectId, Integer page, String title){

        Map<String, Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(page, SIZE, SORT);
        Page<WorkSpaceEntity> workSpaces = workSpaceRepository
                .findAllByProjectAndTitleContains(new ProjectEntity(projectId), pageable, title);

        List<SimpleWorkSpaceDto> responseDto = SimpleWorkSpaceDto.toDto(workSpaces);

        responseMap.put("isLast", workSpaces.isLast());
        responseMap.put("wordSpaces", responseDto);

        return responseMap;
    }

    @Transactional
    public WorkSpaceEntity updateWorkSpace(Long projectId, Long workSpaceId, DetailWorkSpaceDto dto){

        WorkSpaceEntity workSpace = workSpaceRepository.findByProjectAndId(new ProjectEntity(projectId), workSpaceId)
                .orElseThrow(IllegalArgumentException::new);
        workSpace.update(dto);

        return workSpace;
    }

    @Transactional
    public WorkSpaceEntity deleteWorkSpace(Long projectId, Long workSpaceId){
        return workSpaceRepository.deleteByProjectAndId(new ProjectEntity(projectId), workSpaceId)
                .orElseThrow(IllegalArgumentException::new);
    }

}
