package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.paging.Criteria;
import main.paging.PageMaker;
import main.DTO.WineDTO;
import main.service.wine.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/wine")
public class WineController {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping(value = "/allWineList")
    public String allWineList(Model model, Criteria criteria) {
        logger.debug("allWineList debug >>> ");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(wineService.countWines(criteria));

        model.addAttribute("allWineList", wineService.listPaging(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "/wine/allWineList";
    }

    @RequestMapping(value = "/readOneWine")
    public String readOneWine(WineDTO wineDTO, Model model) {
        logger.debug("readOneWine debugs >>> ");
        model.addAttribute("readOneWine", wineService.readOneWine(wineDTO.getNumber()));
        return "/wine/readOneWine";
    }

    @GetMapping(value = "/searchWineByName")
    public String searchWineByName(Model model, @ModelAttribute("name") WineDTO wineDTO) {
        logger.debug("searchWineByName debugs >>> ");
        model.addAttribute("searchWineByName", wineService.searchWineByName("name"));
        return "/wine/allWineList";
    }
}
