package main.DAO.wine;

import main.DTO.MemberDTO;
import main.DTO.WineDTO;

import java.util.List;

public interface WineDAO {

    public List<WineDTO> selectAllWine();

    public WineDTO readOneWine(int number);

}
