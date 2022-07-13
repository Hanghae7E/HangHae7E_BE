package hanghae7e6.prototype.security.oauth;

import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map <String, Object> attributes;
    private String nameAttributeKey;
    private Long userId;
    private String username;
    private String email;
    private String socialType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
        String username, String email, String socialType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.email = email;
        this.socialType = socialType;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (!userNameAttributeName.equals("sub"))
            return ofKaKao(userNameAttributeName, attributes);

        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                                .username((String) attributes.get("name"))
                                .email((String) attributes.get("email"))
                                .socialType("google")
                                .attributes(attributes)
                                .nameAttributeKey(userNameAttributeName)
                                .build();
    }

    public static OAuthAttributes ofKaKao(String userNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        return OAuthAttributes.builder()
                              .username((String) properties.get("nickname"))
                              .email((String) kakaoAccount.get("email"))
                              .socialType("kakao")
                              .attributes(attributes)
                              .nameAttributeKey(userNameAttributeName)
                              .build();
    }


    public UserEntity toEntity() {
        return UserEntity.builder().username(username).email(email).userRole(UserRole.USER).socialType(socialType).build();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, Object> getHashMapAttributes() {

        Map<String, Object> hashMapAttributes = new HashMap<>(this.attributes);

        hashMapAttributes.put("socialType", socialType);
        hashMapAttributes.put("userId", userId);

        return hashMapAttributes;
    }
}
