package hanghae7e6.prototype.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_USER", "사용자"),
    POST_AUTHOR("ROLE_POST_AUTHOR", "프로젝트글 작성자");

    private final String key;
    private final String title;
}
