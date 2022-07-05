package hanghae7e6.prototype.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        List<TagEntity> tags = tagService.findAll();
        List<TagResponseDto> body = TagResponseDto.getDtos(tags);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


}