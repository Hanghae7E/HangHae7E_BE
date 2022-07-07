package hanghae7e6.prototype.profile.controller;


import hanghae7e6.prototype.profile.dto.PositionRequest;
import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    PositionRepository positionRepository;

    @GetMapping
    public List<PositionEntity> getAllPositions() {
        List <PositionEntity> allPositions = positionRepository.findAll();

        return allPositions;
    }

    @PostMapping
    public void getAllPositions(@RequestBody PositionRequest positionRequest) {
        if (positionRequest.getPositionName() == null) return;

        positionRepository.save(PositionEntity.builder().positionName(positionRequest.getPositionName()).build());
    }
}
