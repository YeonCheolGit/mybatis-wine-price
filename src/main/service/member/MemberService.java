package main.service.member;

import main.DTO.MemberDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface MemberService {

    Boolean registerMember(MemberDTO memberDTO);

    Object login(MemberDTO memberDTO, HttpServletRequest request);

    int duplicated_nickName_chk(MemberDTO memberDTO);

    int duplicated_email_chk(MemberDTO memberDTO);

    Boolean updateMember(MemberDTO memberDTO, HttpServletRequest request);

    void sendEmail(MemberDTO memberDTO, String div);

    @Transactional
    String findPwd(MemberDTO memberDTO) throws IOException;

    List<MemberDTO> allMemberList();

    void enabled_control(MemberDTO memberDTO);
}
