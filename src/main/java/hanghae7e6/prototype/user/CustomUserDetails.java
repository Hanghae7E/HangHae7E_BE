package hanghae7e6.prototype.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String email;
    private String socialType;
    private  Collection<? extends GrantedAuthority> authorities;

    @Builder
    public CustomUserDetails(Long id, String email, String socialType,
        Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.socialType = socialType;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static CustomUserDetails toUserDetails(UserEntity user) {
        ArrayList <GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getKey()));

        return CustomUserDetails.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .email(user.getEmail())
                                .socialType(user.getSocialType())
                                .authorities(authorities)
                                .build();
    }

}
