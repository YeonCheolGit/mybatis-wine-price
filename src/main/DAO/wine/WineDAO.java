package main.DAO.wine;

import main.paging.Criteria;
import main.DTO.WineDTO;

import java.util.List;

public interface WineDAO {

    List<WineDTO> selectAllWine();

    WineDTO readOneWine(int number);

    List<WineDTO> searchWineByName(String name);

    List<WineDTO> listPaging(Criteria criteria);

    int countWines(Criteria criteria);
}
