package pl.janda.jmessenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.janda.jmessenger.infrastructure.cqrs.CommandBus;
import pl.janda.jmessenger.infrastructure.cqrs.HandlerRegistry;

@SpringBootApplication
public class JMessenger {

	public static void main(String[] args) {
		SpringApplication.run(JMessenger.class, args);
	}

	@Bean
	public HandlerRegistry handlerRegistry(ApplicationContext applicationContext) {
		return new HandlerRegistry(applicationContext);
	}

	@Bean
	public CommandBus commandBus(HandlerRegistry handlerRegistry) {
		return new CommandBus(handlerRegistry);
	}

}
