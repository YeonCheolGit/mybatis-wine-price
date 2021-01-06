package main.service.wine;

import main.DTO.Criteria;
import main.DTO.WineDTO;

import java.util.List;

public interface WineService {

    public List<WineDTO> selectAllWine();

    public WineDTO readOneWine(int number);

    public List<WineDTO> searchWineByName(String name);

    public List<WineDTO> listPaging(Criteria criteria);

    public int countWines(Criteria criteria);
}
