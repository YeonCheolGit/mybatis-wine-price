package main.service.member;

import main.DTO.MemberDTO;

public interface MemberService {

    void registerMember(MemberDTO memberDTO);

//    void registerMember(String id, String pwd, String name);

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedIdChk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);
}
