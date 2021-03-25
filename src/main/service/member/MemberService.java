package main.service.member;

import main.DTO.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

public interface MemberService extends UserDetailsService{

    void registerMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedNickNameChk(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);

    void sendEmail(MemberDTO memberDTO, String div);

    String findPwd(MemberDTO memberDTO) throws IOException;

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
