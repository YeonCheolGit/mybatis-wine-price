package main.service.wine;

import main.DTO.WineDTO;

import java.util.List;

public interface WineService {

    public List<WineDTO> selectAllWine();

    public WineDTO readOneWine(int number);
}
