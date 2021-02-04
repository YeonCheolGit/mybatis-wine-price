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
    String food;
    int price;
    String URL;

    public WineDTO() {
    }

    public WineDTO(String name, int price, String URL) {
        this.name = name;
        this.price = price;
        this.URL = URL;
    }

    public WineDTO(BigInteger number, String name, String food, int price, String URL) {
        this.number = number;
        this.name = name;
        this.food = food;
        this.price = price;
        this.URL = URL;
    }
}
