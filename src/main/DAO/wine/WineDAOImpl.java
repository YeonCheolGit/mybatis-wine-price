package main.DAO.wine;

import main.DTO.MemberDTO;
import main.DTO.WineDTO;
import main.paging.SearchCriteria;
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
    public WineDTO readOneWine(int number) {
        return sqlSession.selectOne(nameSpace + ".readOneWine", number);
    }

    @Override
    public List<WineDTO> listPaging(SearchCriteria searchCriteria) {
        return sqlSession.selectList(nameSpace + ".listPaging", searchCriteria);
    }

    @Override
    public int countWines(SearchCriteria searchCriteria) {
        return sqlSession.selectOne(nameSpace + ".countWines", searchCriteria);
    }

    @Override
    public List<String> search(String keyword) {
        return sqlSession.selectList(nameSpace + ".search", keyword);
    }

    @Override
    public void addWines(String wine) {
        sqlSession.insert(nameSpace + ".addWines", wine);
    }
}
