package main.service.wine;

import main.DAO.wine.WineDAO;
import main.DTO.Criteria;
import main.DTO.MemberDTO;
import main.DTO.WineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineServiceImpl implements WineService {
    private final WineDAO wineDAO;

    public WineServiceImpl(WineDAO wineDAO) {
        this.wineDAO = wineDAO;
    }

    @Override
    public List<WineDTO> selectAllWine() {
        return wineDAO.selectAllWine();
    }

    @Override
    public WineDTO readOneWine(int number) {
        return wineDAO.readOneWine(number);
    }

    @Override
    public List<WineDTO> searchWineByName(String name) {
        return wineDAO.searchWineByName(name);
    }

    @Override
    public List<WineDTO> listPaging(Criteria criteria) {
        return wineDAO.listPaging(criteria);
    }
}
