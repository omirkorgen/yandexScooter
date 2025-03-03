package praktikum.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

public class MainPage {
    private final WebDriver driver;

    private final By questionsSection = By.className("Home_FAQ__3uVm4");

    private final By orderButtonOnHeader = By.className("Button_Button__ra12g");

    private final By orderButtonOnMain = By.className("Button_Middle__1CSJM");

    public WebElement getListItemById(String itemId) {
        String xpath = ".//div[@id='accordion__heading-" + itemId + "']";
        return driver.findElement(By.xpath(xpath));
    };
    public WebElement getAnswerTextById(String itemId) {
        String xpath = ".//div[@id='accordion__panel-" + itemId + "']";
        return driver.findElement(By.xpath(xpath));
    };
    private final By acceptCookieButton = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void scrollQuestionSection() {
        WebElement element = driver.findElement(questionsSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }


    public void clickOnQuestion(String itemId){
        getListItemById(itemId).click();
    }

    public String getActualQuestionText(String itemId){
        return getListItemById(itemId).getText();
    }

    public String getActualAnswerText(String itemId){
        return getAnswerTextById(itemId).getText();
    }

    public void checkAnswerIsVisible(String itemId){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='accordion__heading-" + itemId + "']")));
        assertTrue(driver.findElement(By.xpath(".//div[@id='accordion__heading-" + itemId + "']")).isDisplayed());
    }

    public void buttonOrderClick(String orderButtonPosition) {
        String bottomName = "Кнопка 1";
        if(orderButtonPosition.equals(bottomName)){
            driver.findElement(orderButtonOnHeader).click();
        } else {
            WebElement scrollElement = driver.findElement(orderButtonOnMain);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollElement);
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderButtonOnMain));
            scrollElement.click();
        }
    }

}
