package news.today.notification.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Component
public class Driver {

    @Value("${chrome.driver}")
    private String chromeDriver;

    public WebDriver setDriver() {
        Path path = Paths.get(chromeDriver);
        System.setProperty("webdriver.chrome.driver", path.toString());

        // 옵션 설정
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
//        options.addArguments("headless");   // 브라우저 안띄움
        options.addArguments("--disable-gpu");  // gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음

        WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return driver;
    }

    public String searchNews(String category) {

        WebDriver driver = setDriver();
        driver.get("https://news.zum.com/front?c=01&cm=news_lnb");

        return null;
    }
}
