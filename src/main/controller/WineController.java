package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.service.wine.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/wine")
public class WineController {
    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    /*
    All wine list
     */
    @GetMapping(value = "/allWineList")
    public String allWineList(Model model) {
        List<WineDTO> allWineList = wineService.selectAllWine();
        model.addAttribute("allWineList", allWineList);
        return "wine/wineList";
    }
}
