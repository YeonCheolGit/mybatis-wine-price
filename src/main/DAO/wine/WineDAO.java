package main.DAO.wine;

import main.DTO.Criteria;
import main.DTO.MemberDTO;
import main.DTO.WineDTO;

import java.util.List;

public interface WineDAO {

    public List<WineDTO> selectAllWine();

    public WineDTO readOneWine(int number);

    public List<WineDTO> searchWineByName(String name);

    public List<WineDTO> listPaging(Criteria criteria);
}
