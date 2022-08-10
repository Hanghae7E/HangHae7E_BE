package hanghae7e6.prototype.workspace.redis;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusDto {

    private Long userId;
    private Long workSpaceId;


    public UserStatusDto(UserStatusEntity entity){
        this.userId = entity.getUserId();
        this.workSpaceId = entity.getWorkSpaceId();

    }
}
