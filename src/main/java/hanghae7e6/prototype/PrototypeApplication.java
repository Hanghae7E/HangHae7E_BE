package hanghae7e6.prototype;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@EnableJpaAuditing
@SpringBootApplication
public class PrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner setUp(Starter starter){
//		return args -> starter.redisTest();
//	}
}

