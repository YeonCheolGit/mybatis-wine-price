package main.service.member;

import main.DTO.MemberDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MemberService {

    void registerMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedIdChk(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);

    void sendEmail(MemberDTO memberDTO, String div);

    void findPw(HttpServletResponse resp, MemberDTO memberDTO) throws IOException;

}
