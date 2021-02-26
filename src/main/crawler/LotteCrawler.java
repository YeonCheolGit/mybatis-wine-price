package main.crawler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import main.DTO.WineDTO;
import main.controller.WineController;
import main.service.wine.WineService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
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
        driver.get("https://www.lotteon.com/search/render/render.ecn?render=nqapi&platform=" + // 롯데마트 와인코너 초기 화면
                "pc&collection_id=301&u9=navigate&u8=LM40004056&login=Y&mallId=4");

        /*
         * 와인의 갯수가 정해져 있지 않음.
         * ArrayList --> LinkedList로 변경 (데이터의 추가 속도)
         */
        List<String> nameList = new LinkedList<>(); // 와인 이름 저장 할 배열
        List<Integer> priceList = new LinkedList<>(); // 와인 가격 저장 할 배열
        String URL = "https://www.lotteon.com/search/search/search.ecn?render=search&platform=pc&q="; // 각 와인 이동 링크

        try {
            int page = 1; // 시작 페이지
            while (page < 4) { // 총 와인 페이지
                List<WebElement> wineNamesElement = driver.findElements(By.xpath("//div[@class='srchProductUnitTitle']")); // 와인 이름
                List<WebElement> winePricesElement = driver.findElements(By.xpath("//span[@class='srchCurrentPrice']")); // 와인 가격

                String name;
                String price;
                int priceInt;

                for (WebElement wineName : wineNamesElement) { // 한 페이지씩 와인 이름 가져온 후 배열에 저장
                    name = wineName.getText().trim(); // 요소 중 텍스트만 추출
                    nameList.add(name);
                }
                for (WebElement winePrice : winePricesElement) { // 한 페이지씩 와인 가격 가져온 후 배열에 저장
                    price = winePrice.getText().replaceAll("[^0-9]", ""); // 와인 가격에서 숫자만 가져오기
                    priceInt = Integer.parseInt(price); // String 와인 가격값을 int로 변환
                    priceList.add(priceInt);
                }

                WebElement button = driver.findElement(By.xpath("//*[@id='c301_navigate1']/div/a[3]")); // 버튼 위치 먼저 찾고
                Thread.sleep(2000); // 버튼 찾고 잠시 대기
                button.sendKeys(Keys.ENTER); // 버튼 클릭
                Thread.sleep(5000); // 다음 페이지 로딩 시간 대기

                page++;
                System.out.println("lotte page >>>> " + page);
            }

            for (int i = 0; i < nameList.size(); i++) { // 배열에 저장된 3페이지 분량, 한번에 DB에 저장
                wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i), URL));
            }

        } catch (InterruptedException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            logger.debug("lotte end >>> ");
            driver.close();
        }
    }
}
