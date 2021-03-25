package main.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
@Getter @Setter
public class MemberDTO implements UserDetails {
    private String email;
    private String pwd;
    private String nickName;
    private Date regDate;
    private Boolean enabled;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(role));
        return auth;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public MemberDTO() {
    }

    public MemberDTO(String email, String pwd, String nickName, Date regDate, Boolean enabled, String role) {
        this.email = email;
        this.pwd = pwd;
        this.nickName = nickName;
        this.regDate = regDate;
        this.enabled = enabled;
        this.role = role;
    }
}



