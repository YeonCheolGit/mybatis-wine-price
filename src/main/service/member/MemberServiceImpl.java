package main.service.member;

import main.DAO.member.MemberDAO;
import main.DTO.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public void registerMember(MemberDTO memberDTO) {
        memberDAO.registerMember(memberDTO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return memberDAO.login(memberDTO);
    }

    @Override
    public int loginChk(MemberDTO memberDTO) {
        return memberDAO.loginChk(memberDTO);
    }

    @Override
    public int idChk(MemberDTO memberDTO) {
        return memberDAO.idChk(memberDTO);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        memberDAO.updateMember(memberDTO);
    }
}
