package hanghae7e6.prototype.profile.service;


import hanghae7e6.prototype.exception.AbstractException;
import hanghae7e6.prototype.profile.entity.PositionEntity;
import hanghae7e6.prototype.profile.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionEntity getPositionByName(String positionName) throws AbstractException {
        return positionRepository.findByPositionName(positionName);
    }
}
