package hanghae7e6.prototype.config;

import hanghae7e6.prototype.security.jwt.JwtAuthEntryPoint;
import hanghae7e6.prototype.security.jwt.JwtFilter;
import hanghae7e6.prototype.security.jwt.JwtProvider;
import hanghae7e6.prototype.security.oauth.CustomHttpSessionOAuth2AuthorizationRequestRepository;
import hanghae7e6.prototype.security.oauth.OAuth2AuthenticationSuccessHandler;
import hanghae7e6.prototype.user.OAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final OAuth2UserServiceImpl oAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomHttpSessionOAuth2AuthorizationRequestRepository customHttpSessionOAuth2AuthorizationRequestRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomHttpSessionOAuth2AuthorizationRequestRepository sessionOAuth2AuthorizationRequestRepository() {
        return new CustomHttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors().configurationSource(configurationSource())

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthEntryPoint)

            .and()
            .authorizeRequests()
            .antMatchers("/api","/api/login", "/api/register", "/login/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/recruitPost").permitAll()
            .antMatchers(HttpMethod.GET, "/api/recruitPost/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/login/**").permitAll()

//            .antMatchers("/api/user").hasRole(UserRole.USER.name())
            .anyRequest()
            .permitAll()
//            .authenticated()

            .and()
            .logout()
            .logoutSuccessUrl("/")


            .and()
            .oauth2Login()
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization")
            .authorizationRequestRepository(sessionOAuth2AuthorizationRequestRepository())
//            .defaultSuccessUrl("/login-success")
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .userInfoEndpoint()
            .userService(oAuth2UserService);

            http.addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
