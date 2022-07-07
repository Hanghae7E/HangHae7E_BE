package hanghae7e6.prototype.profile.controller;


import hanghae7e6.prototype.profile.dto.PositionRequest;
import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
