package mp.application.emailsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmailSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSystemApplication.class, args);

	}
}