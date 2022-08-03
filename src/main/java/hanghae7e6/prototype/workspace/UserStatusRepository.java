package hanghae7e6.prototype.workspace;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class UserStatusRepository {

    private RedisTemplate<String, Long>  userIdTemplate;


    @Autowired
    public UserStatusRepository(RedisTemplate<String, Long> userIdTemplate) {
        this.userIdTemplate = userIdTemplate;
    }

    public String addUserStatus(String uuid, Long userId){
        ListOperations<String,Long> userIdOps =  userIdTemplate.opsForList();

        if(Objects.isNull(userIdOps.index(uuid, 0L))){
            userIdOps.set(uuid, 0L, userId);
        }else{
            userIdOps.rightPush(uuid, userId);
        }

//        return  userIdOps.
        return "123";
    }

//    public List<Long> getUserStatus(String uuid){
//        return Optional.ofNullable(userIdOps.get(uuid))
//                .orElseGet(() -> new ArrayList<>());
//
//    }
}
