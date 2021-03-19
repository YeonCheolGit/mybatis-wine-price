package main.service.wine;

import main.DAO.wine.WineDAO;
import main.DTO.WineDTO;
import main.paging.SearchCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WineServiceImpl implements WineService {
    private final WineDAO wineDAO;

    public WineServiceImpl(WineDAO wineDAO) {
        this.wineDAO = wineDAO;
    }

    @Override
    public List<WineDTO> listPaging(SearchCriteria searchCriteria) {
        return wineDAO.listPaging(searchCriteria);
    }

    @Override
    public List<WineDTO> orderByPrice(SearchCriteria searchCriteria) {
        return wineDAO.orderByPrice(searchCriteria);
    }

    @Override
    public int countWines(SearchCriteria searchCriteria) {
        return wineDAO.countWines(searchCriteria);
    }

    @Override
    public List<String> search(String keyword) {
        return wineDAO.search(keyword);
    }

    @Override
    public void addWineNamePrice(WineDTO wine) {
        wineDAO.addWineNamePrice(wine);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void wineSearchCount(String hit) {
        wineDAO.wineSearchCount(hit);
    }

    @Override
    public List<WineDTO> realtimeWineSearchCount() {
        return wineDAO.realtimeWineSearchCount();
    }
}
