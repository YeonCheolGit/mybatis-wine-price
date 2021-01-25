package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.MemberDTO;
import main.DTO.WineDTO;
import main.paging.PageMaker;
import main.paging.SearchCriteria;
import main.service.wine.WineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/wine")
public class WineController {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @RequestMapping(value = "/readOneWine")
    public String readOneWine(WineDTO wineDTO, Model model) {
        logger.debug("readOneWine debugs >>> ");
        model.addAttribute("readOneWine", wineService.readOneWine(wineDTO.getNumber()));
        return "/wine/readOneWine";
    }

    @GetMapping(value = "/searchBar")
    public String searchBar(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                            Model model) {
        logger.debug("searchBar >>> ");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        pageMaker.setTotalCount(wineService.countWines(searchCriteria));

        model.addAttribute("allWineList", wineService.listPaging(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);

        return "wine/allWineList";
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public List<String> search(HttpServletRequest request) {
        logger.debug("search >>> ");
        return wineService.search(request.getParameter("term"));
    }

//    @GetMapping(value = "/crawler")
//    public void crawler(HttpServletRequest request) throws IOException {
//        logger.debug("crawler >>> ");
//
//        int number = 0;
//
//        while (number <= 7) {
//            ++number;
//            Document doc1 = Jsoup
//                    .connect("http://www.ssg.com/search.ssg?target=all&query=와인&ctgId=6000099422&ctgLv=3&ctgLast=Y&parentCtgId=6000099420&count=100&page=" + number).get();
//            Elements title1 = doc1.select("a.clickable > em.tx_ko");
//
//            int count = 0;
//
//            for (Element element : title1) {
//                count++;
//                String win = element.text();
//                wineService.addWines(win);
//                System.out.println(count + " >> " + win + " >> " + count);
//            }
//            System.out.println(number + "페이지 끝");
//            System.out.println("======================= \n");
//        }
//    }
}
