package main.DAO.wine;

import main.DTO.WineDTO;
import main.paging.SearchCriteria;

import java.util.List;

public interface WineDAO {

    WineDTO readOneWine(int number);

    List<WineDTO> listPaging(SearchCriteria searchCriteria);

    int countWines(SearchCriteria searchCriteria);

    List<String> search(String keyword);
}
