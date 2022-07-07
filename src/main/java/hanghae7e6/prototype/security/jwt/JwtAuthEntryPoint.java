package hanghae7e6.prototype.security.jwt;

import com.google.gson.Gson;
import hanghae7e6.prototype.exception.ErrorCode;
import hanghae7e6.prototype.exception.ErrorRes;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        ErrorRes errorRes = ErrorRes.builder().status(401).message(
            ErrorCode.LOGIN_REQUIRED.getMessage()).build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(errorRes));
    }
}
