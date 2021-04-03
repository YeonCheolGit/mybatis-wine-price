package main.DAO.member;

import main.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final SqlSession sqlSession;

    @Autowired
    public MemberDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private static final String nameSpace = "mapper.member";

    @Override
    public List<MemberDTO> allMemberList() {
        return sqlSession.selectList(nameSpace + ".allMemberList");
    }

    @Override
    public void enabledPause(MemberDTO memberDTO) {
        sqlSession.update(nameSpace + ".enabledPause", memberDTO);
    }


    @Override
    @Transactional
    public void registerMember(MemberDTO memberDTO) {
        sqlSession.insert(nameSpace + ".registerMember", memberDTO);
    }

    @Override
    @Transactional
    public MemberDTO login(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".login", memberDTO);
    }

    @Override
    public int duplicated_email_chk(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".duplicated_email_chk", memberDTO);
    }

    @Override
    public int duplicated_nickName_chk(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".duplicated_nickName_chk", memberDTO);
    }

    @Override
    @Transactional
    public void updateMember(MemberDTO memberDTO) {
        sqlSession.update(nameSpace + ".memberUpdate", memberDTO);
    }
}
