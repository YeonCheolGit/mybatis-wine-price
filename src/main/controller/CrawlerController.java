package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.service.wine.WineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/crawler")
public class CrawlerController {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public CrawlerController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping(value = "/emart")
    public String crawler() throws IOException {
        logger.debug("crawler >>> ");

        int number = 1; // 시작 페이지

        while (number <= 8) { // 와인 카테고리 총 페이지
            Document doc1 = Jsoup
                    .connect("http://www.ssg.com/search.ssg?target=all&query=" +
                            "와인&ctgId=6000099422&ctgLv=3&ctgLast=Y&parentCtgId=6000099420&" +
                            "count=100&page=" + number).get();

            Elements wineNames= doc1.select("a.clickable > em.tx_ko"); // 와인 이름
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
        return "redirect:wine/searchBar";
    }

    @GetMapping(value = "/lotte")
    public String lotteCrawler() throws InterruptedException {
        WebDriver driver = new SafariDriver();
        driver.manage().window().setSize(new Dimension(2000, 2000));

        try {
            driver.get("https://www.lotteon.com/search/render/render.ecn?render=nqapi&platform=" +
                    "pc&collection_id=301&u9=navigate&u8=LM40004056&login=Y&mallId=4");

            int page = 0; // 시작 페이지
            while (page <= 3) { // 총 와인 페이지
                List<WebElement> wineNamesElement = driver.findElements(By.xpath("//div[@class='srchProductUnitTitle']")); // 와인 이름
                List<WebElement> winePricesElement = driver.findElements(By.xpath("//span[@class='srchCurrentPrice']")); // 와인 가격

                ArrayList<String> nameList = new ArrayList<>(); // 와인 이름 저장 할 배열
                ArrayList<Integer> priceList = new ArrayList<>(); // 와인 가격 저장 할 배열

                String name = null;
                String price = null;
                int priceInt = 0;
                String URL = "https://www.lotteon.com/search/search/search.ecn?render=search&platform=pc&q=";

                for (WebElement wineName : wineNamesElement) { // 한 페이지씩 와인 이름 가져온 후 배열에 저장
                    name = wineName.getText().trim();
                    nameList.add(name);
                }
                for (WebElement winePrice : winePricesElement) { // 한 페이지씩 와인 가격 가져온 후 배열에 저장
                    price = winePrice.getText().replaceAll("[^0-9]", ""); // 와인 가격에서 숫자만 가져오기
                    priceInt = Integer.parseInt(price); // String 와인 가격값을 int로 변환
                    priceList.add(priceInt);
                }
                for (int i = 0; i < wineNamesElement.size(); i++) { // 한 페이지씩 저장된 이름, 가격 배열을 DB에 저장
                    wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i), URL));
                }
                driver.findElement(By.xpath("//a[@class='srchPaginationNext']")).click(); // 다음 페이지 클릭
                Thread.sleep(5000); // 페이지 로딩 시간
                page++;
            }
        } finally {
            driver.quit();
        }
        return "redirect:wine/searchBar";
    }
}
