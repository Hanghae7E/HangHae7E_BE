package hanghae7e6.prototype.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtProvider {
    private final String secretKey;
    private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60;  // 초단위, 1시간

    private final UserDetailsService userDetailsService;

    private JwtProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.userDetailsService = userDetailsService;
    }

    public String createToken(OAuth2User oAuth2User) {
        Date now = new Date();

        String userId = Objects.requireNonNull(oAuth2User.getAttribute("userId")).toString();
        String email = oAuth2User.getAttribute("email");
        String socialType = oAuth2User.getAttribute("socialType");

        Claims claims = Jwts.claims();

        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("social-type", socialType);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String userId = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                            .get("userId", String.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(
            userDetails, "", userDetails.getAuthorities());
    }

    public void setJwtToHeader(String jwt, HttpServletResponse response) {
        response.addHeader("Authorization", jwt);
        response.addHeader("Token-type", "Bearer");
    }
}
