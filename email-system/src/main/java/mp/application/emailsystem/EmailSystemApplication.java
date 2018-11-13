package mp.application.emailsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmailSystemApplication {

	/**
	 * FIRST BUILD WITH FLYWAY DISABLED TO BUILD DATABASE WITH HIBERNATE THEN OPTIONALLY ENABLE
	 * FLYWAY TO LOAD SCRIPTS
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmailSystemApplication.class, args);

	}
}