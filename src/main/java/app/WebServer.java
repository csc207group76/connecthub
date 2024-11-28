package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"app", "api"})
public class WebServer {
    public static void main(String[] args) {
        final Repositories repositories = new Repositories();

		// Application Start
        SpringApplication.run(WebServer.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				repositories.closeDatabaseConnection();
				System.out.println("Discconected to the database.");
			}
		});
    }
}
