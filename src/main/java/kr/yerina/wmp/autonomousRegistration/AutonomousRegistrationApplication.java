package kr.yerina.wmp.autonomousRegistration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.TimeZone;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class AutonomousRegistrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AutonomousRegistrationApplication.class, args);
	}
}
