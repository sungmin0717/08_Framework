package game.video.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication( exclude = {SecurityAutoConfiguration.class})

public class GvideoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GvideoProjectApplication.class, args);
	}

}
