package main.service.wine;

import main.DTO.MemberDTO;
import main.DTO.WineDTO;
import main.paging.SearchCriteria;

import java.util.List;

public interface WineService {

    void addWines(String wine);

    WineDTO readOneWine(int number);

    List<WineDTO> listPaging(SearchCriteria searchCriteria);

    int countWines(SearchCriteria searchCriteria);

    List<String> search(String keyword);

}
