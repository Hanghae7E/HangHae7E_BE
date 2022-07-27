package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.user.CustomUserDetails;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class RecruitPostController {

    RecruitPostService recruitPostService;

    @Autowired
    public RecruitPostController(
            RecruitPostService recruitPostService){
        this.recruitPostService = recruitPostService;
    }


    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> getPosts(
        @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort, @RequestParam(required = false, value = "tags") Long tagId) {

        Direction sortDirection = Direction.ASC;

        if (sort.equals("new")) {
            sortDirection = Direction.DESC;
            sort = "createdAt";
        }
        if (sort.equals("due")) sort = "recruitDueTime";

        PageRequest pageRequest = PageRequest.of(page, size, sortDirection, sort);
        Map<String, Object> body = recruitPostService.getPosts(pageRequest, tagId);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @GetMapping("/recruitPost/{postId}")
    public ResponseEntity<DetailPostResponseDto> getPost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long postId){

        Long currentUserId = userDetails != null ? userDetails.getId() : null;
        DetailPostResponseDto body = recruitPostService.getPost(currentUserId, postId);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @PostMapping("/recruitPost")
    public ResponseEntity<?> createPost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute PostRequestDto requestDto) throws IOException{

        if (requestDto.getProjectEndTime() == null || requestDto.getRecruitDueTime() == null || requestDto.getProjectStartTime() == null)
            throw new InvalidException(ErrorCode.EMPTY_BODY);

        recruitPostService.createPost(userDetails, requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/recruitPost/{postId}")
    public ResponseEntity<?> updatePost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long postId,
            @ModelAttribute PostRequestDto requestDto) throws IOException {

        if (requestDto.getProjectEndTime() == null || requestDto.getRecruitDueTime() == null || requestDto.getProjectStartTime() == null)
            throw new InvalidException(ErrorCode.EMPTY_BODY);

        recruitPostService.updatePost(userDetails, postId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }




    @DeleteMapping("recruitPost/{postId}")
    public ResponseEntity<?> deletePost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long postId){

        recruitPostService.deletePost(userDetails, postId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
