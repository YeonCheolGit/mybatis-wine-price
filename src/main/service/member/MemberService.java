package main.service.member;

import main.DTO.MemberDTO;

import java.io.IOException;

public interface MemberService {

    void registerMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedNickNameChk(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);

    void sendEmail(MemberDTO memberDTO, String div);

    String findPwd(MemberDTO memberDTO) throws IOException;
}
