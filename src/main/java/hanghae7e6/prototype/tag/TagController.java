package hanghae7e6.prototype.tag;

import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TagController {

    TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping("/tag")
    public ResponseEntity<List<TagResponseDto>> findAll(){

        List<TagResponseDto> body = tagService.findAll().stream()
                .map(TagResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PostMapping("/tag")
    public void insertTag(@RequestBody TagRequest tagRequest) {
        if (tagRequest.getBody() == null) throw new InvalidException(ErrorCode.EMPTY_BODY);


        tagService.insertTag(tagRequest);
    }

}
