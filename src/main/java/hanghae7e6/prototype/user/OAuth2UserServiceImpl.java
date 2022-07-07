package hanghae7e6.prototype.user;

import hanghae7e6.prototype.profile.entity.ProfileEntity;
import hanghae7e6.prototype.profile.repository.ProfileRepository;
import hanghae7e6.prototype.security.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        UserEntity user = userRepository.findBySocialTypeAndEmail(attributes.getSocialType(), attributes.getEmail()).orElse(saveUserAndProfile(attributes));
        attributes.setUserId(user.getId());

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().getKey())), attributes.getHashMapAttributes(), attributes.getNameAttributeKey());
    }

    @Transactional
    public UserEntity saveUserAndProfile(OAuthAttributes attributes) {
        UserEntity user = attributes.toEntity();
        ProfileEntity profile = ProfileEntity.builder().user(user).build();

        userRepository.save(user);
        profileRepository.save(profile);

        return user;
    }
}
