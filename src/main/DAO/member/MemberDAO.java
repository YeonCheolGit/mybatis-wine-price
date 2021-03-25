package main.DAO.member;

import main.DTO.MemberDTO;

public interface MemberDAO {

    void registerMember(MemberDTO memberDTO) ;

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    int duplicatedNickNameChk(MemberDTO memberDTO);

    MemberDTO readMember(String memberDTO);

    void updateMember(MemberDTO memberDTO);

    MemberDTO getUserById(String userName);
}
