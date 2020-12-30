package main.service.member;

import main.DTO.MemberDTO;

import java.util.List;

public interface MemberService {

    public void registerMember(MemberDTO memberDTO);

    public MemberDTO login(MemberDTO memberDTO);
}
