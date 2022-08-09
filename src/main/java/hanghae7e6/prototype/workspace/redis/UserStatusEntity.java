package hanghae7e6.prototype.workspace.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RedisHash("User_Status")
public class UserStatusEntity {

    @Id
    private String id;

    @Indexed
    private String uuid;

    @Indexed
    private Long userId;


    private Long workSpaceId;


    public void update(Long workSpaceId){
        this.workSpaceId = workSpaceId;
    }

}
