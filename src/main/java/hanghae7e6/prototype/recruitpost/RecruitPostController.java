package hanghae7e6.prototype.recruitpost;

import hanghae7e6.prototype.recruitpost.dto.DetailPostResponseDto;
import hanghae7e6.prototype.recruitpost.dto.PostParamDto;
import hanghae7e6.prototype.recruitpost.dto.PostRequestDto;
import hanghae7e6.prototype.recruitpost.dto.SimplePostResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @RequestParam("tag") String tag){

        PostParamDto requestDto = PostParamDto.builder()
                .limit(limit)
                .page(page)
                .sort(sort)
                .tag(tag).build();

        PostParamDto.validate(requestDto);

        List<SimplePostResponseDto> body =
                recruitPostService.getPosts(requestDto);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @GetMapping("/recruit/{postId}")
    public ResponseEntity<DetailPostResponseDto> getPost(
            @PathVariable Long postId){

        DetailPostResponseDto body = recruitPostService.getPost(postId);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @PostMapping("/recruitPost")
    public ResponseEntity<String> createPost(
//            @AuthenticationPrinciple UserDetails userDetails
            @ModelAttribute @Valid PostRequestDto requestDto){

        recruitPostService.createPost(requestDto);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PutMapping("/recruitPost/{postId}")
    public ResponseEntity<String> updatePost(
//            UserDetails userDetails,
            @PathVariable Long postId,
            @ModelAttribute PostRequestDto postRequestDto){

//        recruitPostService.updatePost()

        return new ResponseEntity<>(HttpStatus.OK);
    }




//    @DeleteMapping("recruitPost/{postId}")




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){

        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .peek(System.out::println)
                .findFirst()
                .orElseGet(() -> "there's no error message");

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
