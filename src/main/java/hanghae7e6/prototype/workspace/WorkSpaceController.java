package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.dto.DetailWorkSpaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class WorkSpaceController {

    private WorkSpaceService workSpaceService;

    @Autowired
    public WorkSpaceController(WorkSpaceService workSpaceService) {
        this.workSpaceService = workSpaceService;
    }


    @PostMapping("/team/{teamId}/workSpace")
    public ResponseEntity<String> createWorkSpace(
            @PathVariable Long teamId){
        workSpaceService.createWorkSpace(teamId);

        return ResponseEntity.ok().body("SUCCESS");
    }


    @GetMapping("/team/{teamId}/workSpace/{workSpaceId}")
    public ResponseEntity<DetailWorkSpaceDto> getWorkSpace(
            @PathVariable Long teamId,
            @PathVariable Long workSpaceId){

        WorkSpaceEntity workSpace = workSpaceService.getWorkSpace(teamId, workSpaceId);
        DetailWorkSpaceDto responseDto = new DetailWorkSpaceDto(workSpace);

        return ResponseEntity.ok().body(responseDto);
    }


    @GetMapping("/team/{teamId}/workSpace")
    public ResponseEntity<Map<String, Object>> getWorkSpaces(
            @PathVariable Long teamId,
            @RequestParam("page") Integer page){

        page = 1 <= page? page - 1 : 0;

        Map<String, Object> responseMap =
                workSpaceService.getWorkSpaces(teamId, page);

        return ResponseEntity.ok().body(responseMap);
    }


    @DeleteMapping("/team/{teamId}/workSpace/{workSpaceId}")
    public ResponseEntity<String> deleteWorkSpace(
            @PathVariable Long teamId,
            @PathVariable Long workSpaceId){

        workSpaceService.deleteWorkSpace(teamId, workSpaceId);

        return ResponseEntity.ok().body("SUCCESS");
    }

    @PutMapping("/team/{teamId}/workSpace/{workSpaceId}")
    public ResponseEntity<String> updateWorkSpace(
            @PathVariable Long teamId,
            @PathVariable Long workSpaceId,
            @RequestBody DetailWorkSpaceDto requestDto){

        workSpaceService.updateWorkSpace(teamId, workSpaceId, requestDto);

        return ResponseEntity.ok().body("SUCCESS");
    }

}
