package news.today.notification.search;

import news.today.notification.model.Category;
import news.today.notification.model.News;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class Driver {

    @Value("${chrome.driver}")
    private String chromeDriver;

    @Value("${zum.site.url}")
    private String zumUrl;

    /**
     * Chrome Driver 세팅
     * @return
     */
    public WebDriver setDriver() {
        Path path = Paths.get(chromeDriver);
        System.setProperty("webdriver.chrome.driver", path.toString());

        // 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
        options.addArguments("headless");   // 브라우저 안띄움
        options.addArguments("--disable-gpu");  // gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    /**
     * 카테고리 별 뉴스 데이터 수집
     * @param category
     * @return
     */
    public List<News> searchNews(String category) {
        WebDriver driver = setDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String newUrl = zumUrl;
        int categoryNum = Category.of(category).getNum();
        if (categoryNum < 10) newUrl += "0";
        newUrl += Integer.toString(categoryNum);

        driver.get(newUrl);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("#news_sec_main_wrap > div"))
        );

        // 기사 수집
        List<News> newsList = new ArrayList<>();

        String mainSelector = "#news_sec_main_wrap > div > section.section_top_news > div.main_slot";
        String subSelector = "#news_sec_main_wrap > div > section.section_top_news > ul.sub_slots > li";
        String companySelector = "strong";
        String titleSelector = "a > h2";
        String urlSelector = "a";
        String contentSelector = "a > div.text";

        List<WebElement> webElements = new ArrayList<>();
        webElements.add(driver.findElement(By.cssSelector(mainSelector)));        // 메인 기사 수집
        webElements.addAll(driver.findElements(By.cssSelector(subSelector)));     // 서브 기사 수집

        if (webElements.size() > 0) {
            for (WebElement element: webElements) {
                newsList.add(
                        News.builder()
                                .company(element.findElement(By.cssSelector(companySelector)).getText())
                                .title(element.findElement(By.cssSelector(titleSelector)).getText())
                                .url(element.findElement(By.cssSelector(urlSelector)).getAttribute("href"))
                                .content(element.findElement(By.cssSelector(contentSelector)).getText())
                                .build()
                );
            }
        }

        driver.quit();
        return newsList;
    }
}
