package main.DAO.member;

import main.DTO.MemberDTO;

import java.util.List;

public interface MemberDAO {

    void registerMember(MemberDTO memberDTO) ;

    MemberDTO login(MemberDTO memberDTO);

    int duplicated_email_chk(MemberDTO memberDTO);

    int duplicated_nickName_chk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);

    List<MemberDTO> allMemberList();

    void enabledPause(MemberDTO memberDTO);
}
