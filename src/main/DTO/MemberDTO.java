package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
@Getter @Setter
public class MemberDTO {

    private String username;
    private String password;
    private String nickName;
    private Date regDate;
    private int enabled;
    private String role;



    public MemberDTO() {
    }

    public MemberDTO(String username, String password, String nickName, Date regDate, int enabled, String role) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.regDate = regDate;
        this.enabled = enabled;
        this.role = role;
    }
}



