package main.controller;

import main.crawler.EmartCrawler;
import main.crawler.LotteCrawler;
import main.service.wine.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController {

    private final WineService wineService;

    public CrawlerController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public void crawler() {
//        Runnable emartCrawler = new EmartCrawler(wineService);
        Runnable lotteCrawler = new LotteCrawler(wineService);

//        Thread emartThread = new Thread(emartCrawler);
        Thread lotteThread = new Thread(lotteCrawler);

//        emartThread.start();
        lotteThread.start();

//        return "redirect:wine/searchBarAndPagination";
    }
}
