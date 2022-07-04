package hanghae7e6.prototype;

import hanghae7e6.prototype.domain.entity.UserEntity;
import hanghae7e6.prototype.domain.entity.UserRole;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostRepository;
import hanghae7e6.prototype.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PrototypeApplication {


	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}


    @Bean
    public CommandLineRunner demo(
            RecruitPostRepository recruitPostRepository,
            UserRepository userRepository) {
        return (args) -> {
            UserEntity user1 = UserEntity.builder()
                    .email("email")
                    .username("username")
                    .socialType("kakao")
                    .phoneNumber("000-000-0000")
                    .imageUrl("url")
                    .userRole(UserRole.USER)
                    .build();

            UserEntity user2 = userRepository.save(user1);


            for(int i=0; i<10; i++) {
                RecruitPostEntity recruitPostEntity =
                        RecruitPostEntity.builder()
                                .user(user2)
                                .body("프로젝트 모집 내용")
                                .title("프로젝트 모집")
                                .imageUrl("abc/abc")
                                .projectStartTime(LocalDate.now())
                                .projectEndTime(LocalDate.now())
                                .recruitDueTime(LocalDate.now())
                                .totalMemderCount(i)
                                .build();

                recruitPostRepository.save(recruitPostEntity);

            }

            // 토큰
            String userId = Long.toString(user2.getId());
            String email = user2.getEmail();
            String socialType = user2.getSocialType();

            Claims claims = Jwts.claims();

            claims.put("userId", userId);
            claims.put("email", email);
            claims.put("social-type", socialType);

            Date now = new Date();

            String tokens = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + (60*60*1000)))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("test".getBytes()))
                    .compact();

            System.out.println(tokens);
        };
    }
}
