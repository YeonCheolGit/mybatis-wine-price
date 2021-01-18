package main.DAO.member;

import main.DTO.MemberDTO;

public interface MemberDAO {

    void registerMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    int loginChk(MemberDTO memberDTO);

    int idChk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);
}
