package hanghae7e6.prototype;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
=======
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
>>>>>>> 94bb954 (FEAT: 사용자 기능 - 소셜 회원가입, 로그인 기능 구현(구글), JWT 적용)

@EnableJpaAuditing
@SpringBootApplication
public class PrototypeApplication {


	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}


    @Bean
    public CommandLineRunner demo(
			RecruitPostRepository recruitPostRepository) {
        return (args) -> {
            for(int i=0; i<10; i++) {
                RecruitPostEntity recruitPostEntity =
                        RecruitPostEntity.builder()
                                .body("프로젝트 모집 내용")
                                .title("프로젝트 모집")
                                .imageUrl("abc/abc")
                                .projectStartTime(LocalDateTime.now())
                                .projectEndTime(LocalDateTime.now())
                                .recruitDueTime(LocalDateTime.now())
                                .totalMemderCount(i)
                                .build();

                recruitPostRepository.save(recruitPostEntity);
            }
        };
    }
}
