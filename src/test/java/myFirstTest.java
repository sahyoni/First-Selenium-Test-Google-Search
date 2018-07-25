import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class myFirstTest {

    @Test
    public void myFirstTestSelenium() {

        WebDriver driver = new FirefoxDriver();

        String baseUrl = "https://www.google.com/";
        String expectedTitle = "Google";
        String actualTitle = "";

        driver.get(baseUrl);

        actualTitle = driver.getTitle();


        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        WebElement searchArea = driver.findElement(By.name("q"));
        searchArea.sendKeys("Testo\n");
        searchArea.submit();

        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        for (WebElement webElement : findElements) {
            System.out.println(webElement.getAttribute("href"));
        }

        //driver.close();

    }
}
