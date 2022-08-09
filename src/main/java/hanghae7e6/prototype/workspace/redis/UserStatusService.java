package hanghae7e6.prototype.workspace.redis;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStatusService {

    private UserStatusRepository userStatusRepository;


    public UserStatusService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }


    public List<UserStatusEntity> findAllMemberByUuid(String uuid){
        return userStatusRepository.findAllByUuid(uuid);
    }


    public UserStatusEntity setMemberStatus(String uuid, Long userId, Long workSpaceId){
        UserStatusEntity userStatus;

        Optional<UserStatusEntity> userStatusOp = userStatusRepository.findByUserIdAndUuid(userId, uuid);

        if(userStatusOp.isEmpty()){
             userStatus = UserStatusEntity.builder()
                    .userId(userId)
                    .uuid(uuid)
                    .workSpaceId(workSpaceId)
                    .build();

        }else{
            userStatus = userStatusOp.get();
            userStatus.update(workSpaceId);
        }

        return userStatusRepository.save(userStatus);
    }

    public void deleteStatus(Long userId, String uuid){
        userStatusRepository.deleteByUserIdAndUuid(userId, uuid)
                .orElseThrow(IllegalArgumentException::new);
    }

}
