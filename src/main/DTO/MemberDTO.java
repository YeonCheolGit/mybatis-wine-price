package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter @Setter
public class MemberDTO {
    private String email;
    private String pwd;
    private String id;
    private Date regDate;

    public MemberDTO() {
    }

    public MemberDTO(String email, String pwd, String id, Date regDate) {
        this.email = email;
        this.pwd = pwd;
        this.id = id;
        this.regDate = regDate;
    }
}
