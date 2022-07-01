package hanghae7e6.prototype.dto;

import hanghae7e6.prototype.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequest {

    private final String email;


    private final String username;

    private final String phoneNumber;

    @Builder
    public UserRequest(String email, String username, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public UserEntity toEntity() {
        return UserEntity.builder().email(email).username(username).phoneNumber(phoneNumber).build();
    }

}
