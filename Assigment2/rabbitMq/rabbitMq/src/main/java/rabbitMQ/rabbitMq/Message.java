package rabbitMQ.rabbitMq;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long deviceId;
    private double measurementValue;
    private Long timestamp;
}
