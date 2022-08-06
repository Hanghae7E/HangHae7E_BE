package hanghae7e6.prototype.project.redis;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusService {

    private UserStatusRepository userStatusRepository;

    public UserStatusService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    public void getMemberStatus(String uuid){
        List<UserStatusEntity> members = userStatusRepository.findAllByUuid(uuid);
    }

//    public void setUserStatus(String uuid, ){
//
//    }
}
