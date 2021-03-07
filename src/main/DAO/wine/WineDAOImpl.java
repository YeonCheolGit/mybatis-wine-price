package main.DAO.wine;

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
    public List<WineDTO> listPaging(SearchCriteria searchCriteria) {
        return sqlSession.selectList(nameSpace + ".listPaging", searchCriteria);
    }

    @Override
    public List<WineDTO> orderByPrice(SearchCriteria searchCriteria) {
        return sqlSession.selectList(nameSpace + ".orderByPrice", searchCriteria);
    }

    @Override
    public int countWines(SearchCriteria searchCriteria) {
        return sqlSession.selectOne(nameSpace + ".countWines", searchCriteria);
    }

    @Override
    public List<String> search(String keyword) {
        return sqlSession.selectList(nameSpace + ".autocomplete", keyword);
    }

    @Override
    public void addWineNamePrice(WineDTO wine) {
        sqlSession.insert(nameSpace + ".addWineNamePrice", wine);
    }

}
