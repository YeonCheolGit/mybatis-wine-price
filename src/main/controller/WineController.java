package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.service.wine.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/wine")
public class WineController {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    /*
    All wine list
     */
    @GetMapping(value = "/allWineList")
    public String allWineList(Model model) {
        logger.info("allWineList");
        List<WineDTO> allWineList = wineService.selectAllWine();
        model.addAttribute("allWineList", allWineList);
        return "/wine/allWineList";
    }

    @RequestMapping(value = "/readOneWine")
    public String readOneWine(WineDTO wineDTO, Model model) {
        logger.info("readOneWine");
        model.addAttribute("readOneWine", wineService.readOneWine(wineDTO.getNumber()));
        return "wine/readOneWine";
    }
}
