import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class myFirstTest {

    WebDriverWait wait;

    @Test
    public void myFirstTestSelenium() {

        WebDriver driver = new FirefoxDriver();

        String expectedTitle = openGoogle(driver);

        String actualTitle = driver.getTitle();


        checkTitle(expectedTitle, actualTitle);
        searchForTestoAndGetTheFirstResultAndClickOnIt(driver);

        hoverOverProduktInNavBarAndClickFeuchte(driver);

        //driver.close();
    }

    private void searchForTestoAndGetTheFirstResultAndClickOnIt(WebDriver driver) {
        WebElement searchArea = driver.findElement(By.name("q"));
        searchArea.sendKeys("Testo\n");
        searchArea.submit();

        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        System.out.println(findElements.get(0).getAttribute("href"));

        findElements.get(0).click();
    }

    private void checkTitle(String expectedTitle, String actualTitle) {
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
    }

    private String openGoogle(WebDriver driver) {
        String baseUrl = "https://www.google.com/";
        String expectedTitle = "Google";
        String actualTitle = "";

        driver.get(baseUrl);
        return expectedTitle;
    }

    private void hoverOverProduktInNavBarAndClickFeuchte(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("Produkte"));

        Actions action = new Actions(driver);

        action.moveToElement(element).build().perform();

        driver.findElement(By.linkText("Feuchte")).click();
    }


}
