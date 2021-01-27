package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.service.wine.WineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.*;

@Controller
@Slf4j
@RequestMapping(value = "/crawler")
public class CrawlerController {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public CrawlerController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping(value = "/")
    public void crawler() throws IOException {
        logger.debug("crawler >>> ");

        int number = 0;

        while (number <= 7) {
            ++number;
            Document doc1 = Jsoup
                    .connect("http://www.ssg.com/search.ssg?target=all&query=" +
                            "와인&ctgId=6000099422&ctgLv=3&ctgLast=Y&parentCtgId=6000099420&" +
                            "count=100&page=" + number).get();

            Elements wineNames= doc1.select("a.clickable > em.tx_ko");
            Elements winePrices = doc1.select("div.opt_price > em.ssg_price");

            String name = null;
            String price = null;

            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> priceList = new ArrayList<>();

            for (Element element : wineNames) {
                name = element.text();
                nameList.add(name);
            }

            for (Element element : winePrices) {
                price = element.text();
                priceList.add(price);
            }
            for (int i = 0; i < nameList.size(); i++) {
                wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i)));
            }
        }
    }
}
