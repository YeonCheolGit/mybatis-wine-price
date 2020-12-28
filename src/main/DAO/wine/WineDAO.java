package main.DAO.wine;

import main.DTO.WineDTO;

import java.util.List;

public interface WineDAO {

    public List<WineDTO> selectAllWine();

}
