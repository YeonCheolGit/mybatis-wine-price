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

    public WineDTO() {
    }

    public WineDTO(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public WineDTO(int number, String name, String food, String price) {
        this.number = number;
        this.name = name;
        this.food = food;
        this.price = price;
    }
}
