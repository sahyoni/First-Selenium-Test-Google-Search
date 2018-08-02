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

    private WebDriver webDriver = new FirefoxDriver();

    WebDriverWait wait = new WebDriverWait(webDriver, 5);

    @Test
    public void myFirstTestSelenium() {

        String expectedTitle = openGoogle(webDriver);

        String actualTitle = webDriver.getTitle();


        checkTitle(expectedTitle, actualTitle);
        searchForTestoAndGetTheFirstResultAndClickOnIt(webDriver);

        checkIfTheDropDownNavBarHaveElements();

        hoverOverProduktInNavBarAndClickFeuchte(webDriver);

        clickKokiesAkzeptieren();

        clickTemperatur();

        clickSortByLowerPrise();


        //driver.close();
    }

    private void clickSortByLowerPrise() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("sbSelector"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Preis: niedrigster zuerst"))).click();
    }

    private void clickKokiesAkzeptieren() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Akzeptieren"))).click();
    }

    private void clickTemperatur() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Temperatur"))).click();
    }

    private void checkIfTheDropDownNavBarHaveElements() {
        List<WebElement> listOfMenuElement = webDriver.findElements(By.className("submenu"));
        System.out.println(listOfMenuElement.size());
    }

    private void searchForTestoAndGetTheFirstResultAndClickOnIt(WebDriver driver) {
        WebElement searchArea = driver.findElement(By.name("q"));
        searchArea.sendKeys("Testo\n");
        // OR //searchArea.submit();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

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
