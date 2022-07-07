package hanghae7e6.prototype.applicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    private ApplicantRepository applicantRepository;

    @Autowired
    ApplicantService(ApplicantRepository applicantRepository){
        this.applicantRepository = applicantRepository;
    }



}
