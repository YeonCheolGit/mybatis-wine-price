package main.DAO.member;

import main.DTO.MemberDTO;

import java.util.List;

public interface MemberDAO {

    void registerMember(MemberDTO memberDTO) ;

    MemberDTO login(MemberDTO memberDTO);

    int duplicatedEmailChk(MemberDTO memberDTO);

    int duplicatedNickNameChk(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);

    List<MemberDTO> allMemberList();

    int enabledPause(MemberDTO memberDTO);
}
