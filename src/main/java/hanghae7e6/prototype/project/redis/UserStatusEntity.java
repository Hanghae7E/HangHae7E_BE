package hanghae7e6.prototype.project.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("User_Status")
public class UserStatusEntity {

    @Id
    private Long userId;

    private String uuid;

    private Long workSpaceId;

}
