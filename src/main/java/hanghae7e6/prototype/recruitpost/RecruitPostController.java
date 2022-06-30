package hanghae7e6.prototype.recruitpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<SimplePostResponseDto> body =
                recruitPostService.getPosts(limit, page-1, sort, tag);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
