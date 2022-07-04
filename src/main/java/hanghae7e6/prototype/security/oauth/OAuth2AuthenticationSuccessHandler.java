package hanghae7e6.prototype.security.oauth;

import hanghae7e6.prototype.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${frontend.url}")
    private String FRONTEND_URL;
    private final JwtProvider jwtProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String jwt = jwtProvider.createToken((OAuth2User) authentication.getPrincipal());

        String url = makeRedirectUrl(jwt);

        jwtProvider.setJwtToHeader(jwt, response);

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String jwt) {

        return UriComponentsBuilder
                .fromUriString(FRONTEND_URL + "/login/callback?" + "jwt=" + jwt)
                .build()
                .toUriString();
    }
}
