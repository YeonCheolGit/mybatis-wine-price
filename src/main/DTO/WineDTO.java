package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Getter @Setter
public class WineDTO {

    BigInteger id;
    String name;
    int price;
    String URL;
    int searchCount;

    public WineDTO() {
    }

    public WineDTO(String name, int price, String URL) {
        this.name = name;
        this.price = price;
        this.URL = URL;
    }

    public WineDTO(BigInteger id, String name, int price, String URL, int searchCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.URL = URL;
        this.searchCount = searchCount;
    }
}
