package main.DAO.member;

import main.DTO.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private final SqlSession sqlSession;
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public MemberDAOImpl(SqlSession sqlSession, SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSession = sqlSession;
        this.sqlSessionTemplate = sqlSessionTemplate;
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
    public MemberDTO readMember(String memberDTO) {
        return sqlSession.selectOne(nameSpace + ".readMember", memberDTO);
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

    @Override
    public MemberDTO getUserById(String userName) {
        return sqlSession.selectOne(nameSpace + "getUserById", userName);
    }
}
