package hanghae7e6.prototype.milestone;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestone")
@RequiredArgsConstructor
public class MilestoneController {
    private final MilestoneService milestoneService;

    @PostMapping
    public MilestoneResponse createMilestone(@RequestBody MilestoneRequest milestoneRequest) throws Exception{
        return milestoneService.createMilestone(milestoneRequest);
    }

    @GetMapping
    public List<MilestoneResponse> getAllMilestones() throws Exception{
        return milestoneService.getAllMilestones();
    }

    @GetMapping("/{milestoneId}")
    public MilestoneResponse getMilestone(@PathVariable Long milestoneId) throws Exception{
        return milestoneService.getMilestone(milestoneId);
    }

    @PutMapping("/{milestoneId}")
    public MilestoneResponse updateMilestone(@PathVariable Long milestoneId, @RequestBody MilestoneRequest milestoneRequest) throws Exception{
        return milestoneService.updateMilestone(milestoneId, milestoneRequest);
    }

    @DeleteMapping("/{milestoneId}")
    public Long deleteMilestone(@PathVariable Long milestoneId){
        return milestoneService.deleteMilestone(milestoneId);
    }

}
