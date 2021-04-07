package main.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageListener {
    SimpMessagingTemplate template;

    public MessageListener(SimpMessagingTemplate template) {
        this.template = template;
    }

    /*
     * KafkaConstants.KAFKA_TOPIC: 메세지를 읽어 들이는 Topic
     * KafkaConstants.GROUP_ID: 메세지를 보내는 group id
     */
    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )

    /*
     * 목적지(web socket)로 메세지 보냄.
     */
    public void listen(MessageModel messageModel) {
        log.info("카프카 리스너를 통해 메세지 보냄");
        template.convertAndSend("/topic/group", messageModel);
    }
}
