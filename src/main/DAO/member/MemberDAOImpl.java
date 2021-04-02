package main.DAO.member;

import main.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void registerMember(MemberDTO memberDTO) {
        sqlSession.insert(nameSpace + ".registerMember", memberDTO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".login", memberDTO);
    }

    @Override
    public int duplicatedEmailChk(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".duplicatedEmailChk", memberDTO);
    }

    @Override
    public int duplicatedNickNameChk(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".duplicatedNickNameChk", memberDTO);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        sqlSession.update(nameSpace + ".memberUpdate", memberDTO);
    }
}
