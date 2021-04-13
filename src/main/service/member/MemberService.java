package main.service.member;

import main.DTO.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface MemberService {

    MemberDTO securityLogin(String username);

    @Transactional
    Boolean registerMember(MemberDTO memberDTO);

    Object login(MemberDTO memberDTO, HttpServletRequest request);

    int duplicatedNickNameChk(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    Boolean updateMember(MemberDTO memberDTO, HttpServletRequest request);

    void sendEmail(MemberDTO memberDTO, String div);

    @Transactional
    String findPwd(MemberDTO memberDTO) throws IOException;

    List<MemberDTO> allMemberList();

    int enabledControl(MemberDTO memberDTO);
}
