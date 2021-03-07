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

    /*
     * 쓰레드로 등록한 이마트, 롯데마트 크롤러 객체 등록 후 실행
     */
    @GetMapping
    public void crawler() {
        Thread lotteThread = new Thread(new LotteCrawler(wineService));
//        Thread emartThread = new Thread(new EmartCrawler(wineService));

        lotteThread.start();
//        emartThread.start();
    }
}
