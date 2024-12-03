package rabbitMQ.rabbitMq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication

public class RabbitMqApplication implements org.springframework.boot.CommandLineRunner {

	private final MessageService messageService ;

	public RabbitMqApplication(MessageService messageService) {
		this.messageService = messageService;
	}


	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			// Sending a sample device change event
			/*System.out.println("****************************************");
			System.out.println("Sending device change event...");
			messageService.sendDeviceChangeEvent(3L, "create"); // Example: Creating a device with ID 1
*/
			// Sending measurements to the measurementQueue
			System.out.println("Sending measurements...");
			messageService.sendMessage();



		} catch (Exception e) {
			System.err.println("Error running the message service: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
