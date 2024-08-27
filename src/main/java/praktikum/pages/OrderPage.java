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
    private final By inputDelivery = By.className("Order_MixedDatePicker__3qiay");
    private final By DeliveryDate = By.xpath(".//div[@class = 'react-datepicker__day react-datepicker__day--020']");
    private final By inputRentalPeriod = By.className("Dropdown-placeholder");
    public WebElement listRentalPeriod(String rentalPeriod) {
        String xpath = ".//div[text()='" + rentalPeriod + "']";
        return driver.findElement(By.xpath(xpath));
    };
    public WebElement scooterColorCheckBox(String scooterColor) {
        String xpath = ".//label[@for='" + scooterColor + "']";
        return driver.findElement(By.xpath(xpath));
    };

    private final By courierComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    private final By acceptOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickInputRentalPeriod(){
        driver.findElement(inputRentalPeriod).click();
    }

    public void clickAcceptOrder() {
        driver.findElement(acceptOrderButton).click();
    }

    public void clickCourierComment(String comment) {
        driver.findElement(courierComment).sendKeys(comment);
    }

    public void clickOnInputName(String name){
        driver.findElement(inputName).sendKeys(name);
    }

    public void clickOnInputDelivery(){
        driver.findElement(inputDelivery).click();
    }

    public void selectDeliveryDate(){
        driver.findElement(DeliveryDate).click();
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

    public void clickScooterColorCheckBox(String scooterColor){
        scooterColorCheckBox(scooterColor).click();
    }

    public void clickRentalPeriod(String rentalPeriod){
        listRentalPeriod(rentalPeriod).click();
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

    public void checkCalendarIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker")));
        assertTrue(driver.findElement(By.className("react-datepicker")).isDisplayed());
    }

    public void checkRentalPeriodListIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Dropdown-menu")));
        assertTrue(driver.findElement(By.className("Dropdown-menu")).isDisplayed());
    }

    public void checkAcceptOrderButtonIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']")));
        assertTrue(driver.findElement(By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']")).isDisplayed());
    }

    public void checkNextOrderPageIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Order_Content__bmtHS .Order_Header__BZXOb")));
        assertTrue(driver.findElement(By.cssSelector(".Order_Content__bmtHS .Order_Header__BZXOb")).isDisplayed());
    }

    public void checkSuccessOrderPageIsVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));
        assertTrue(driver.findElement(By.className("Order_ModalHeader__3FDaJ")).isDisplayed());
    }

}
