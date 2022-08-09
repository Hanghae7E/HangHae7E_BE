package hanghae7e6.prototype.milestone;

import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.NotFoundException;
import hanghae7e6.prototype.projectmember.ProjectMemberEntity;
import hanghae7e6.prototype.projectmember.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectMemberRepository projectMemberRepository;

    //PostMapping
    @Transactional
    public MilestoneResponse createMilestone(MilestoneRequest milestoneRequest) {
        ProjectMemberEntity author = projectMemberRepository.findById(milestoneRequest.getAuthorId()).orElseThrow(
                ()->new NotFoundException(ErrorCode.PROJECT_MEMBER_NOT_FOUND));
        ProjectMemberEntity personInCharge = projectMemberRepository.findById(milestoneRequest.getPersonInChargeId()).orElseThrow(
                ()->new NotFoundException(ErrorCode.PROJECT_MEMBER_NOT_FOUND));
        MilestoneEntity milestone = milestoneRequest.toEntity(author, personInCharge);
        return new MilestoneResponse(milestoneRepository.save(milestone));
    }

    //GetMapping
    @Transactional
    public List<MilestoneResponse> getAllMilestones() {
        List<MilestoneEntity> milestoneList = milestoneRepository.findAll();
        List<MilestoneResponse> milestoneResponseList = new ArrayList<>();
        for (MilestoneEntity milestone: milestoneList){
            milestoneResponseList.add(new MilestoneResponse(milestone));
        }
        return milestoneResponseList;
    }

    public MilestoneResponse getMilestone(Long milestoneId){
        MilestoneEntity milestone = milestoneRepository.findById(milestoneId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MILESTONE_NOT_FOUND));
        return new MilestoneResponse(milestone);
    }

    //PutMapping
    public MilestoneResponse updateMilestone(Long milestoneId, MilestoneRequest milestoneRequest){
        ProjectMemberEntity personInCharge = projectMemberRepository.findById(milestoneRequest.getPersonInChargeId()).orElseThrow(
                ()->new NotFoundException(ErrorCode.PROJECT_MEMBER_NOT_FOUND));
        MilestoneEntity milestone = milestoneRepository.findById(milestoneId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MILESTONE_NOT_FOUND));
        milestone.updateMilestone(milestoneRequest, personInCharge);
        return new MilestoneResponse(milestone);
    }

    //DeleteMapping
    public Long deleteMilestone(Long milestoneId){
        milestoneRepository.deleteById(milestoneId);
        return milestoneId;
        //"삭제 되었습니다"
    }

}
