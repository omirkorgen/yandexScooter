package praktikum.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;
    private final By goButton = By.cssSelector(".Header_Button__28dPO");
    private final By orderField = By.className("Input_Input__1iN_Z");
    private final By statusButton = By.className("Header_Link__1TAG7");
    //private final By orderScooterButtonInHeader = By.className("Button_Button__ra12g");
    //private final By orderScooterButtonInMainBlock = By.className("Button_Middle__1CSJM");

    public WebElement orderButton(String orderButtonVersion) {
        String xpath = String.format("%s", orderButtonVersion);
        return driver.findElement(By.className(xpath));
    };

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

    public void clickAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }

    public void clickOrderButtonInMainPage(String orderButtonVersion) {
        orderButton(orderButtonVersion).click();

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
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='accordion__heading-" + itemId + "']")));
        assertTrue(driver.findElement(By.xpath(".//div[@id='accordion__heading-" + itemId + "']")).isDisplayed());
    }

}
