package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.paging.PageMaker;
import main.paging.SearchCriteria;
import main.service.wine.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

//    @RequestMapping(value = "/readOneWine")
//    public String readOneWine(WineDTO wineDTO, Model model) {
//        logger.info("readOneWine debugs >>> ");
//        model.addAttribute("readOneWine", wineService.readOneWine(wineDTO.getNumber()));
//        return "/wine/readOneWine";
//    }

    @GetMapping(value = "/searchBarAndPagination")
    public String searchBarAndPagination(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                                         Model model) {
        logger.debug("searchBar >>> ");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        pageMaker.setTotalCount(wineService.countWines(searchCriteria));

        model.addAttribute("allWineList", wineService.listPaging(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);

        return "wine/allWineList";
    }

    @GetMapping(value = "/orderByPrice")
    public String orderByPrice(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                            Model model) {
        logger.debug("prices >>> ");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        pageMaker.setTotalCount(wineService.countWines(searchCriteria));

        boolean price = true;

        model.addAttribute("allWineList", wineService.orderByPrice(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("orderByPrice", price);

        return "wine/allWineList";
    }

    @GetMapping(value = "/autocomplete")
    @ResponseBody
    public List<String> search(HttpServletRequest request) {
        logger.debug("search >>> ");
        return wineService.search(request.getParameter("term"));
    }
}
