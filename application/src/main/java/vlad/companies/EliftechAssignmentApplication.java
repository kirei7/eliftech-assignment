package vlad.companies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vlad.companies.config.InMemoryDbInitializer;

@SpringBootApplication
public class EliftechAssignmentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context
				= SpringApplication.run(EliftechAssignmentApplication.class, args);

		if (args[0].equals("h2"))
			context.getBean(InMemoryDbInitializer.class).fillWithInitialData();
	}


}
