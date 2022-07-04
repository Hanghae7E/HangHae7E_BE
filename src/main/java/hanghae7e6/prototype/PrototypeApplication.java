package hanghae7e6.prototype;

import hanghae7e6.prototype.domain.entity.UserEntity;
import hanghae7e6.prototype.domain.entity.UserRole;
import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostRepository;
import hanghae7e6.prototype.repository.UserRepository;
import hanghae7e6.prototype.tag.TagService;
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

}
