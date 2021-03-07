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
import java.util.List;

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
        logger.debug("emart 크롤링 시작 >>> ");

        int number = 1; // 페이지 번호

        String name;
        String price;
        int priceInt;
        String URL = "http://www.ssg.com/search.ssg?target=all&query=";

        /*
         * 와인의 갯수가 정해져 있지 않음.
         * ArrayList --> LinkedList로 변경 (데이터의 추가 속도)
         */
        List<String> nameList = new ArrayList<>(); // 와인 이름을 저장 할 배열
        List<Integer> priceList = new ArrayList<>(); // 와인 가격을 저장 할 배열

        while (number < 7) {
            Document doc1 = Jsoup
                    .connect("http://www.ssg.com/search.ssg?target=all&query=" +
                            "와인&ctgId=6000099422&ctgLv=3&ctgLast=Y&parentCtgId=6000099420&" +
                            "count=100&page=" + number).get();

            Elements wineNames = doc1.select("a.clickable > em.tx_ko"); // 와인 이름
            Elements winePrices = doc1.select("div.opt_price > em.ssg_price"); // 와인 가격

            for (Element element : wineNames) { // 와인 이름만 배열에 저장
                name = element.text();
                nameList.add(name);
            }
            for (Element element : winePrices) {
                price = element.text().replaceAll("[^0-9]", ""); // 와인 가격에서 숫자만 배열에 저장
                priceInt = Integer.parseInt(price);
                priceList.add(priceInt);
            }

            number++;
            System.out.println("이마트 " + number + "페이지 넘어가는 중");

            Thread.sleep(5000); // 다음 페이지 로딩 시간 대기 및 해당 사이트 에러 페이지 방지
        }

        for (int i = 0; i < nameList.size(); i++) { // 배열에 저장된 8페이지 분량, 한번에 DB 저장
            wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i), URL));
        }

        logger.debug("emart 크롤링 끝 >>> ");
    }
}
