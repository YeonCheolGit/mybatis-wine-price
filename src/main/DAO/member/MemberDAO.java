package main.DAO.member;

import main.DTO.MemberDTO;

public interface MemberDAO {

    public void registerMember(MemberDTO memberDTO);

    public MemberDTO login(MemberDTO memberDTO);



}
