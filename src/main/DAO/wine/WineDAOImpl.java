package main.DAO.wine;

import main.DTO.Criteria;
import main.DTO.MemberDTO;
import main.DTO.WineDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WineDAOImpl implements WineDAO {

    private final SqlSession sqlSession;

    public WineDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private static final String nameSpace = "mapper.wine";

    @Override
    public List<WineDTO> selectAllWine() {
        return sqlSession.selectList(nameSpace + ".selectAllWines");
    }

    @Override
    public WineDTO readOneWine(int number) {
        return sqlSession.selectOne(nameSpace + ".readOneWine", number);
    }

    @Override
    public List<WineDTO> searchWineByName(String name) {
        return sqlSession.selectList(nameSpace + ".searchWineByName", name);
    }

    @Override
    public List<WineDTO> listPaging(Criteria criteria) {
        return sqlSession.selectList(nameSpace + ".listPaging", criteria);
    }
}
