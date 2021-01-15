package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter @Setter
public class MemberDTO {
    private String id;
    private String pwd;
    private String name;
    private Date regDate;

    public boolean matchPassword(String pwd) {
        return this.pwd.equals(pwd);
    }

    public MemberDTO() {
    }

    public MemberDTO(String id, String pwd, String name, Date regDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.regDate = regDate;
    }
}
