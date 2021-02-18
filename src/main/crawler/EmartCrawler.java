package main.crawler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.controller.WineController;
import main.service.wine.WineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
@Slf4j
public class EmartCrawler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public EmartCrawler(WineService wineService) {
        this.wineService = wineService;
    }

    @SneakyThrows
    @Override
    public void run() {
        logger.debug("emart start >>> ");

        int number = 1; // 시작 페이지

        while (number <= 8) { // 와인 카테고리 총 페이지
            Document doc1 = Jsoup
                    .connect("http://www.ssg.com/search.ssg?target=all&query=" +
                            "와인&ctgId=6000099422&ctgLv=3&ctgLast=Y&parentCtgId=6000099420&" +
                            "count=100&page=" + number).get();

            Elements wineNames = doc1.select("a.clickable > em.tx_ko"); // 와인 이름
            Elements winePrices = doc1.select("div.opt_price > em.ssg_price"); // 와인 가격

            String name = null;
            String price = null;
            int priceInt = 0;
            String URL = "http://www.ssg.com/search.ssg?target=all&query=";

            ArrayList<String> nameList = new ArrayList<>(); // 와인 이름을 저장 할 배열
            ArrayList<Integer> priceList = new ArrayList<>(); // 와인 가격을 저장 할 배열

            for (Element element : wineNames) {
                name = element.text();
                nameList.add(name);
            }
            for (Element element : winePrices) {
                price = element.text().replaceAll("[^0-9]", ""); // 와인 가격에서 숫자만 가져오기
                priceInt = Integer.parseInt(price);
                priceList.add(priceInt);
            }
            for (int i = 0; i < nameList.size(); i++) {
                wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i), URL));
            }
            number++; // 다음 페이지
        }
        logger.debug("emart end >>> ");
    }
}
