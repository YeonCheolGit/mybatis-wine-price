package main.service.member;

import main.DTO.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public CustomAuthenticationProvider() {
    }

    @Autowired
    public CustomAuthenticationProvider(@Qualifier("memberServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String pwd = (String) authentication.getCredentials();

        MemberDTO memberDTO = (MemberDTO) userDetailsService.loadUserByUsername(email);

        if(!matchPassword(pwd, memberDTO.getPassword())) {
            throw new BadCredentialsException(email);
        }

        if(!memberDTO.isEnabled()) {
            throw new BadCredentialsException(email);
        }

        return new UsernamePasswordAuthenticationToken(email, pwd, memberDTO.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    private boolean matchPassword(String loginPwd, String pwd) {
        return loginPwd.equals(pwd);
    }

}
