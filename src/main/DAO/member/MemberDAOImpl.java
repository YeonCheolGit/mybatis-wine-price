package main.DAO.member;

import main.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final SqlSession sqlSession;

    public MemberDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private static final String nameSpace = "mapper.member";

    @Override
    public void registerMember(MemberDTO memberDTO) {
        sqlSession.insert(nameSpace + ".registerMember", memberDTO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".login", memberDTO);
    }

    @Override
    public int idChk(MemberDTO memberDTO) {
        return sqlSession.selectOne(nameSpace + ".idChk", memberDTO);
    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        return sqlSession.update(nameSpace + ".memberUpdate", memberDTO);
    }
}
