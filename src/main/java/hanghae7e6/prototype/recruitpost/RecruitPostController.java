package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import hanghae7e6.prototype.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class RecruitPostController {

    RecruitPostService recruitPostService;

    @Autowired
    public RecruitPostController(RecruitPostService recruitPostService) {
        this.recruitPostService = recruitPostService;
    }


    @GetMapping("/main")
    public ResponseEntity<List<SimplePostResponseDto>> getPosts(
            @RequestParam("limit") int limit,
            @RequestParam("page") int page,
            @RequestParam("sort") int sort,
            @RequestParam("tag") Long tag){

        PostParamDto requestDto = PostParamDto.builder()
                .limit(limit)
                .page(page)
                .sort(sort)
                .tagId(tag).build();

        PostParamDto.validate(requestDto);

        Page<RecruitPostEntity> posts = recruitPostService.getPosts(requestDto);
        List<SimplePostResponseDto> body = SimplePostResponseDto.getDtos(posts);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @GetMapping("/recruitPost/{postId}")
    public ResponseEntity<DetailPostResponseDto> getPost(
            @PathVariable Long postId){

        RecruitPostEntity post = recruitPostService.getPost(postId);
        DetailPostResponseDto body = new DetailPostResponseDto(post);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @PostMapping("/recruitPost")
    public ResponseEntity<?> createPost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute PostRequestDto requestDto){

        recruitPostService.createPost(userDetails, requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/recruitPost/{postId}")
    public ResponseEntity<?> updatePost(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long postId,
            @ModelAttribute PostRequestDto requestDto){

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




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){

        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .peek(System.out::println)
                .findFirst()
                .orElseGet(() -> "there's no error message");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
