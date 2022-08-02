package hanghae7e6.prototype.config;

import hanghae7e6.prototype.security.jwt.JwtAuthEntryPoint;
import hanghae7e6.prototype.security.jwt.JwtFilter;
import hanghae7e6.prototype.security.jwt.JwtProvider;
import hanghae7e6.prototype.security.oauth.OAuth2AuthenticationSuccessHandler;
import hanghae7e6.prototype.user.OAuth2UserServiceImpl;
import hanghae7e6.prototype.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
            .antMatchers("/api","/api/login", "/api/register").permitAll()
            .antMatchers(HttpMethod.GET, "/api/running-port").permitAll()
            .antMatchers(HttpMethod.GET, "/api/recruitPost").permitAll()
            .antMatchers(HttpMethod.POST, "/api/recruitPost").hasRole(UserRole.USER.name())
            .antMatchers(HttpMethod.PUT, "/api/recruitPost/**").hasRole(UserRole.USER.name())
            .antMatchers(HttpMethod.PUT, "/api/user/**").hasRole(UserRole.USER.name())
            .antMatchers(HttpMethod.DELETE, "/api/recruitPost").hasRole(UserRole.USER.name())
            .antMatchers(HttpMethod.GET, "/api/recruitPost/**").permitAll()

//                 PROJECT기능 TEST
                .antMatchers(HttpMethod.POST, "/api/project/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/project/**").permitAll()

//            .antMatchers("/api/user").hasRole(UserRole.USER.name())
            .anyRequest()
            .permitAll()
//            .authenticated()

            .and()
            .logout()
            .logoutSuccessUrl("/")


            .and()
            .oauth2Login()
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .userInfoEndpoint()
            .userService(oAuth2UserService);

            http.addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

//        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("http://*.*.*.*:8080");
        configuration.addAllowedOrigin("http://*:8080");
//        configuration.addAllowedOrigin("*");
//        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
