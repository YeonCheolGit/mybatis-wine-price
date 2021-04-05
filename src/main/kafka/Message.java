package main.kafka;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
 * Kafka 메세지의 데이터 타입 지정
 */
@Getter @Setter
public class Message implements Serializable {

    private String author;
    private String content;
    private String timestamp; //메세지 작성된 시간



}
