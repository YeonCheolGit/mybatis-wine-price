package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class WineDTO {

    int number;
    String name;
    String food;
    String price;
    String URL;

    public WineDTO() {
    }

    public WineDTO(String name, String price, String URL) {
        this.name = name;
        this.price = price;
        this.URL = URL;
    }

    public WineDTO(int number, String name, String food, String price, String URL) {
        this.number = number;
        this.name = name;
        this.food = food;
        this.price = price;
        this.URL = URL;
    }
}
