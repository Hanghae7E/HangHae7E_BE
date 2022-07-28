package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.recruitpost.SortValue;
import hanghae7e6.prototype.workspace.dto.DetailWorkSpaceDto;
import hanghae7e6.prototype.workspace.dto.SimpleWorkSpaceDto;
import hanghae7e6.prototype.workspace.tempteam.Team;
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
import java.util.stream.Collectors;

@Service
public class WorkSpaceService {

    private WorkSpaceRepository workSpaceRepository;
    final Integer SIZE = 10;
    final Sort SORT = WorkSpaceSort.LATEST_WORKSPACE.getSort();

    @Autowired
    public WorkSpaceService(WorkSpaceRepository workSpaceRepository) {
        this.workSpaceRepository = workSpaceRepository;
    }


    public WorkSpaceEntity createWorkSpace(Long teamId){
        WorkSpaceEntity workSpace = new WorkSpaceEntity(new Team(teamId));
        return workSpaceRepository.save(workSpace);
    }

    @Transactional(readOnly = true)
    public WorkSpaceEntity getWorkSpace(Long teamId, Long workSpaceId){
       return workSpaceRepository.findByTeamAndId(new Team(teamId), workSpaceId)
               .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getWorkSpaces(Long teamId, Integer page){

        Map<String, Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(page, SIZE, SORT);

        Page<WorkSpaceEntity> workSpaces = workSpaceRepository.findAllByTeam(new Team(teamId), pageable);

        List<SimpleWorkSpaceDto> responseDto = workSpaces.toList()
                .stream()
                .map(SimpleWorkSpaceDto::new)
                .collect(Collectors.toList());

        responseMap.put("isLast", workSpaces.isLast());
        responseMap.put("wordSpaces", responseDto);

        return responseMap;
    }

    @Transactional
    public WorkSpaceEntity updateWorkSpace(Long teamId, Long workSpaceId, DetailWorkSpaceDto dto){

        WorkSpaceEntity workSpace = workSpaceRepository.findByTeamAndId(new Team(teamId), workSpaceId)
                .orElseThrow(IllegalArgumentException::new);
        workSpace.update(dto);

        return workSpace;
    }

    @Transactional
    public WorkSpaceEntity deleteWorkSpace(Long teamId, Long workSpaceId){
        return workSpaceRepository.deleteByTeamAndId(new Team(teamId), workSpaceId)
                .orElseThrow(IllegalArgumentException::new);
    }

}
