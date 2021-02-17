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

//    @Override
//    public void registerMember(String id, String pwd, String name) {
//        memberDAO.registerMember(id, pwd, name);
//    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return memberDAO.login(memberDTO);
    }

    @Override
    public int duplicatedIdChk(MemberDTO memberDTO) {
        return memberDAO.duplicatedIdChk(memberDTO);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        memberDAO.updateMember(memberDTO);
    }
}
