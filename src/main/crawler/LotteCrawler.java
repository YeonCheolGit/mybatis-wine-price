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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        logger.debug("lotte 크롤링 시작 >>> ");

        WebDriver driver = new SafariDriver();
        driver.get("https://www.lotteon.com/search/render/render.ecn?render=nqapi&platform=" + // 롯데마트 와인코너 초기 화면
                "pc&collection_id=301&u9=navigate&u8=LM40004056&login=Y&mallId=4");

        /*
         * LinkedList --> ArrayList로 변경 (가장 끝에 순차적으로 add, get 연산은 시간 복잡도(O(1)) 차이X)
         * 하지만 LinkedList의 Head, Tail 때문에 메모리 측면 불리. ArrayList 연속된 메모리에 저장해서 유리.
         */
        List<String> nameList = new ArrayList<>(); // 와인 이름 저장 할 배열
        List<Integer> priceList = new ArrayList<>(); // 와인 가격 저장 할 배열
        String URL = "https://www.lotteon.com/search/search/search.ecn?render=search&platform=pc&q="; // 각 와인 이동 링크

        int page = 1; // 시작 페이지
        while (page < 4) { // 총 와인 페이지
            System.out.println("롯데마트 " + page + "페이지 넘어왔습니다.");
            List<WebElement> wineNamesElement = driver.findElements(By.xpath("//div[@class='srchProductUnitTitle']")); // 와인 이름
            List<WebElement> winePricesElement = driver.findElements(By.xpath("//span[@class='srchCurrentPrice']")); // 와인 가격

            String name, price;
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

            try {
                WebDriverWait waitClickable = new WebDriverWait(driver, 10); // 웹 드라이버 최대 10초간 기다림
//                WebElement nextButton = waitClickable.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"c301_navigate1\"]/div/a[3]"))); // 다음 페이지 버튼 찾아서 클릭 가능할 때까지 기다림.
                WebElement nextButton = waitClickable.until(ExpectedConditions.elementToBeClickable(By.className("srchPaginationNext"))); // 다음 페이지 버튼 찾아서 클릭 가능할 때까지 기다림.
                nextButton.sendKeys(Keys.ENTER); // 다음 페이지 버튼 클릭
                page++;

                System.out.println("롯데마트 " + page + "페이지 넘어가는 중...");
                Thread.sleep(5000); // 다음 페이지 로딩 시간 대기 및 해당 사이트 에러 페이지 방지
            } catch (Exception e) { // 다음 버튼 찾을 수 없는 에러 발생 시, 그동안 가지고 온 데이터 저장
                System.out.println("================ 다음 버튼을 찾을 수 없습니다 ================");
                System.out.println(e.getMessage());
                e.printStackTrace();

                for (int i = 0; i < nameList.size(); i++) { // 배열에 저장된 3페이지 분량, 한번에 DB에 저장
                    wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i), URL));
                }
                logger.debug("lotte 마트 크롤링 끝 >>> ");
                driver.close();
            }
        }

        /*
         * 에러 없이 정상적으로 실행될 때
         */
        for (int i = 0; i < nameList.size(); i++) { // 배열에 저장된 3페이지 분량, 한번에 DB에 저장
            wineService.addWineNamePrice(new WineDTO(nameList.get(i), priceList.get(i), URL));
        }

        logger.debug("lotte 마트 크롤링 끝 >>> ");
        driver.close();
    }
}
