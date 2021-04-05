package main.kafka;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/*
 * Kafka producer configuration
 */
@EnableKafka
@Configuration
public class ProducerConfiguration {

    /*
     * producer 인스턴스 생성
     */
    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    /*
     * KafkaConstants.KAFKA_BROKER: 카프카 실행 주소
     * StringSerializer.class: UTF8 인코딩 및 Key 직렬화 (문자열)
     * JsonSerializer.class: StringSerializer 마찬가지지만, Json 타입
     */
    @Bean
    public Map<String, Object> producerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        configurations.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurations.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configurations;
    }

    /*
     * 생성된 producer 인스턴스를 이용해 kafka topic으로 메세지 보냄
     * Thread - safe
     */
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
