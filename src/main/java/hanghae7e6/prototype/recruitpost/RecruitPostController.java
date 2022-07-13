package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.user.CustomUserDetails;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
            @Nullable @RequestParam("limit") Integer limit,
            @Nullable @RequestParam("page") Integer page,
            @Nullable @RequestParam("sort") Integer sort,
            @Nullable @RequestParam("tag") Long tag){

        PostParamDto requestDto = PostParamDto.builder()
                .limit(limit)
                .offSet(page)
                .sort(sort)
                .tagId(tag).build();

        PostParamDto.validate(requestDto);

        Map<String, Object> body = recruitPostService.getPosts(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @GetMapping("/recruitPost/{postId}")
    public ResponseEntity<DetailPostResponseDto> getPost(
            @PathVariable Long postId){

        DetailPostResponseDto body = recruitPostService.getPost(postId);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @PostMapping("/recruitPost")
    public ResponseEntity<?> createPost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute PostRequestDto requestDto) throws IOException{

        recruitPostService.createPost(userDetails, requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/recruitPost/{postId}")
    public ResponseEntity<?> updatePost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long postId,
            @ModelAttribute PostRequestDto requestDto) throws IOException {

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
