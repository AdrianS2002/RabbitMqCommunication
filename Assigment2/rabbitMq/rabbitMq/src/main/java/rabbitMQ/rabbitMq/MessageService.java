package rabbitMQ.rabbitMq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Send measurements to the measurementQueue.
     */
    public void sendMessage() {
        String filePath = "sensor.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Read measurement from file and build the message
                Long deviceId = Long.parseLong(System.getenv().getOrDefault("id", "1")); // Default to ID=1 if not set
                Message measurementMessage = new Message(deviceId, Double.parseDouble(line), System.currentTimeMillis());

                // Send message to the measurementQueue
                rabbitTemplate.convertAndSend("measurementQueue", objectMapper.writeValueAsString(measurementMessage));
                System.out.println("Measurement sent: " + measurementMessage);

                // Simulate delay between sending messages
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (Exception e) {
            System.err.println("Error sending measurements: " + e.getMessage());
            e.printStackTrace();
        }
    }


}