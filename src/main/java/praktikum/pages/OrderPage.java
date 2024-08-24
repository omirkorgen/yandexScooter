package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderPage {
    private final WebDriver driver;
    private final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    private final By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input");
    public WebElement getListStationById(String metroStationId) {
        String xpath = ".//li[@data-index='" + metroStationId + "']";
        return driver.findElement(By.xpath(xpath));
    };
    private final By inputPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnInputName(String name){
        driver.findElement(inputName).sendKeys(name);
    }

    public void clickOnInputSurname(String surname){
        driver.findElement(inputSurname).sendKeys(surname);
    }

    public void clickOnInputAddress(String address){
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void clickOnMetroStationInput(){
        driver.findElement(metroStation).click();
    }

    public void clickMetroStation(String metroStationId){
        getListStationById(metroStationId).click();
    }

    public void clickOnPhoneNumberInput(String phoneNumber){
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    public void clickOnNextButton(){
        driver.findElement(nextButton).click();
    }

    public void scrollToStationById(String metroStationId) {
        WebElement element = getListStationById(metroStationId);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void checkOrderHeaderIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Header__BZXOb")));
        assertTrue(driver.findElement(By.className("Order_Header__BZXOb")).isDisplayed());
    }

    public void checkStationListIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("select-search__options")));
        assertTrue(driver.findElement(By.className("select-search__options")).isDisplayed());
    }
}
