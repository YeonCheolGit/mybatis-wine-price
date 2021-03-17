package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Getter @Setter
public class WineDTO {

    BigInteger number;
    String name;
    int price;
    String URL;
    int hit;

    public WineDTO() {
    }

    public WineDTO(String name, int price, String URL) {
        this.name = name;
        this.price = price;
        this.URL = URL;
    }

    public WineDTO(BigInteger number, String name, int price, String URL, int hit) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.URL = URL;
        this.hit = hit;
    }
}
