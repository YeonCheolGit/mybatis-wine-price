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
    private String nickName;
    private Date regDate;
    private int enabled;
    private String role;

    public MemberDTO() {
    }

    public MemberDTO(String email, String pwd, String nickName, Date regDate, int enabled, String role) {
        this.email = email;
        this.pwd = pwd;
        this.nickName = nickName;
        this.regDate = regDate;
        this.enabled = enabled;
        this.role = role;
    }
}



