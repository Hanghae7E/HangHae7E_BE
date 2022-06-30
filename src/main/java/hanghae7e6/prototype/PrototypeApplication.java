package hanghae7e6.prototype;

import hanghae7e6.prototype.recruitpost.RecruitPostEntity;
import hanghae7e6.prototype.recruitpost.RecruitPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class PrototypeApplication {


	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}


//    @Bean
//    public CommandLineRunner demo(
//			RecruitPostRepository recruitPostRepository) {
//        return (args) -> {
//            RecruitPostEntity recruitPostEntity =
//                    RecruitPostEntity.builder()
//                            .body("프로젝트 모집 내용")
//                            .title("프로젝트 모집")
//                            .imageUrl("abc/abc")
//                            .projectStartTime(LocalDateTime.now())
//                            .projectEndTime(LocalDateTime.now())
//                            .recruitDueTime(LocalDateTime.now())
//                            .totalMemderCount(5)
//                            .build();
//
//            for(int i=0; i<10; i++){
//                recruitPostRepository.save(recruitPostEntity);
//            }
//        };
//    }
}
