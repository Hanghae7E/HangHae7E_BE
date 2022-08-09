package hanghae7e6.prototype.workspace;

import hanghae7e6.prototype.workspace.dto.DetailWorkSpaceDto;
import hanghae7e6.prototype.workspace.websocket.WorkSpacePubDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class WorkSpaceController {

    private WorkSpaceService workSpaceService;

    @Autowired
    public WorkSpaceController(WorkSpaceService workSpaceService) {
        this.workSpaceService = workSpaceService;
    }


    @PostMapping("/project/{projectId}/workSpace")
    public ResponseEntity<Long> createWorkSpace(
            @PathVariable Long projectId){

        WorkSpaceEntity workSpace = workSpaceService.createWorkSpace(projectId);
        return ResponseEntity.ok().body(workSpace.getId());
    }


    @GetMapping("/project/{projectId}/workSpace/{workSpaceId}")
    public ResponseEntity<DetailWorkSpaceDto> getWorkSpace(
            @PathVariable Long projectId,
            @PathVariable Long workSpaceId){

        WorkSpaceEntity workSpace = workSpaceService.getWorkSpace(projectId, workSpaceId);
        DetailWorkSpaceDto responseDto = new DetailWorkSpaceDto(workSpace);

        return ResponseEntity.ok().body(responseDto);
    }


    @GetMapping("/project/{projectId}/workSpace")
    public ResponseEntity<Map<String, Object>> getWorkSpaces(
            @PathVariable Long projectId,
            @Nullable @RequestParam("page") Integer page){

        page = Objects.isNull(page) || (1 >= page)? 0 : page - 1 ;

        Map<String, Object> responseMap =
                workSpaceService.getWorkSpaces(projectId, page);

        return ResponseEntity.ok().body(responseMap);
    }


    @PutMapping("/project/{projectId}/workSpace/{workSpaceId}")
    public ResponseEntity<String> updateWorkSpace(
            @PathVariable Long projectId,
            @PathVariable Long workSpaceId,
            @RequestBody WorkSpacePubDto requestDto){

       workSpaceService.updateWorkSpace(projectId, workSpaceId, requestDto);

       return ResponseEntity.ok().body("ok");
    }


    @DeleteMapping("/project/{projectId}/workSpace/{workSpaceId}")
    public ResponseEntity<String> deleteWorkSpace(
            @PathVariable Long projectId,
            @PathVariable Long workSpaceId){

        workSpaceService.deleteWorkSpace(projectId, workSpaceId);

        return ResponseEntity.ok().body("ok");
    }


//    @GetMapping("/project/{projectId}/workSpace")
//    public ResponseEntity<Map<String, Object>> searchWorkSpaces(
//            @PathVariable Long projectId,
//            @RequestParam("page") Integer page,
//        @RequestParam("query") String query){
//
//            page = 1 <= page? page - 1 : 0;
//
//        Map<String, Object> responseMap =
//                workSpaceService.searchWorkSpaces(projectId, page, query);
//
//        return ResponseEntity.ok().body(responseMap);
//    }
}