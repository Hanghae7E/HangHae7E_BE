package hanghae7e6.prototype;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostRepository;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagEntity;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagRepository;
import hanghae7e6.prototype.recruitposttag.RecruitPostTagService;
import hanghae7e6.prototype.tag.TagEntity;
import hanghae7e6.prototype.tag.TagService;
import hanghae7e6.prototype.user.UserEntity;
import hanghae7e6.prototype.user.UserRepository;
import hanghae7e6.prototype.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

@EnableJpaAuditing
@SpringBootApplication
public class PrototypeApplication {

	@PersistenceContext
	EntityManager em;

	@PersistenceUnit
	EntityManagerFactory emf;

	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}

}

