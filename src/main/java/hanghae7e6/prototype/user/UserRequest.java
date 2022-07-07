package hanghae7e6.prototype.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequest {

    private final String email;
    private final String username;


    @Builder
    public UserRequest(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UserEntity toEntity() {
        return UserEntity.builder().email(email).username(username).build();
    }

}
