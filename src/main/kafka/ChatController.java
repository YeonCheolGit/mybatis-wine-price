package main.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/kafka")
public class ChatController {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public ChatController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * sned(): Topic으로 데이터 보냄
     */
    @RequestMapping(value = "/publish")
    public void sendMessage(@RequestBody Message message) {
        log.info("Produce message : " + message.toString());
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        return message;
    }
}
