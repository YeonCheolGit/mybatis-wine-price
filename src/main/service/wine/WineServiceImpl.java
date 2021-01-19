package main.service.wine;

import main.DAO.wine.WineDAO;
import main.DTO.WineDTO;
import main.paging.SearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineServiceImpl implements WineService {
    private final WineDAO wineDAO;

    public WineServiceImpl(WineDAO wineDAO) {
        this.wineDAO = wineDAO;
    }

    @Override
    public WineDTO readOneWine(int number) {
        return wineDAO.readOneWine(number);
    }

    @Override
    public List<WineDTO> listPaging(SearchCriteria searchCriteria) {
        return wineDAO.listPaging(searchCriteria);
    }

    @Override
    public int countWines(SearchCriteria searchCriteria) {
        return wineDAO.countWines(searchCriteria);
    }

    @Override
    public List keywordSearch(String memberDTO) {
        return wineDAO.keywordSearch(memberDTO);
    }
}
