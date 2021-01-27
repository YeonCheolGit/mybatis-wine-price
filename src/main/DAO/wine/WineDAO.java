package main.DAO.wine;

import main.DTO.WineDTO;
import main.paging.SearchCriteria;

import java.util.List;

public interface WineDAO {

    void addWineNamePrice(WineDTO wine);

    WineDTO readOneWine(int number);

    List<WineDTO> listPaging(SearchCriteria searchCriteria);

    int countWines(SearchCriteria searchCriteria);

    List<String> search(String keyword);
}
