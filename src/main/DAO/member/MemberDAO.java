package main.DAO.member;

import main.DTO.MemberDTO;

public interface MemberDAO {

    void registerMember(MemberDTO memberDTO) ;

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    int duplicatedIdChk(MemberDTO memberDTO);

    MemberDTO readMember(String memberDTO);

    void updateMember(MemberDTO memberDTO);
}
