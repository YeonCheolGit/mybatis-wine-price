package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class WineDTO {
    String name;
    String origin;
    String type;
    int alcohol;
    int sweetness;
    int acid;
    int body;
    String food;
    int price;

    public WineDTO() {
    }

    public WineDTO(String name, String origin, String type, int alcohol, int sweetness, int acid, int body, String food, int price) {
        this.name = name;
        this.origin = origin;
        this.type = type;
        this.alcohol = alcohol;
        this.sweetness = sweetness;
        this.acid = acid;
        this.body = body;
        this.food = food;
        this.price = price;
    }
}
