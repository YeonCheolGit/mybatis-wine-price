package main.DAO.wine;

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
}
