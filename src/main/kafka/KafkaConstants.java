package main.kafka;

/*
 * KAFKA_TOPIC = "생성한 카프카 토픽"
 * GROUP_ID = "Consumer 식별할 수 있는 그룹"
 * GROUP_ID = Kafka 클러스터에 연결하기 위한 포트"
 */
public class KafkaConstants {
    public static final String KAFKA_TOPIC = "test_topic";
    public static final String GROUP_ID = "test";
    public static final String KAFKA_BROKER = "localhost:9092";
}
