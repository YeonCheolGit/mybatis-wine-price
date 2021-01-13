package main.service.member;

import main.DTO.MemberDTO;

public interface MemberService {

    void registerMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    int idChk(MemberDTO memberDTO);
}
