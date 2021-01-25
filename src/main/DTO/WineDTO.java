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
    int price;

    public WineDTO() {
    }

    public WineDTO(int number, String name, String food, int price) {
        this.number = number;
        this.name = name;
        this.food = food;
        this.price = price;
    }
}
