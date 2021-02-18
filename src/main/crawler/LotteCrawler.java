package main.crawler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.controller.WineController;
import main.service.wine.WineService;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class LotteCrawler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WineController.class);

    private final WineService wineService;

    public LotteCrawler(WineService wineService) {
        this.wineService = wineService;
    }

    @SneakyThrows
    @Override
    public void run() {
        logger.debug("lotte start >>> ");

        WebDriver driver = new SafariDriver();
        driver.manage().window().setSize(new Dimension(2000, 2000)); // 크롤링 화면 브라우저 크기

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
                Thread.sleep(10000); // 다음 페이지 로딩 시간 대기
                page++;
            }
        } finally {
            logger.debug("lotte end >>> ");
            driver.quit();
        }
    }
}
