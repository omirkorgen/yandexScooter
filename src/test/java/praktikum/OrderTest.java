package praktikum;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;

import static praktikum.EnvConfig.BASE_URL;

@RunWith(Parameterized.class)
public class OrderTest {
    @Rule
    public DriverRule factory = new DriverRule();
    private final String orderButtonVersion;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String metroStationId;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public OrderTest(String orderButtonVersion, String name, String surname, String address, String phone, String metroStationId, String rentalPeriod, String scooterColor, String comment) {
        this.orderButtonVersion = orderButtonVersion;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.metroStationId = metroStationId;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] orderData() {
        return new Object[][] {
                {"Кнопка 1", "Антон", "Антонов", "Москва", "87066530492", "6", "сутки", "black", "Оставить перед дверью"},
                {"Кнопка 2", "Влад", "Владимир", "Пушкина", "87066530497", "1", "двое суток", "grey", "Позвонить заранее"},
        };
    }


    @Test
    public void oderScooter() throws Exception {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URL);
        var orderPage = new OrderPage(driver);
        var mainPain = new MainPage(driver);

        mainPain.clickAcceptCookieButton();
        mainPain.buttonOrderClick(orderButtonVersion);

        orderPage.checkOrderHeaderIsVisible();
        orderPage.clickOnInputName(name);
        orderPage.clickOnInputSurname(surname);
        orderPage.clickOnInputAddress(address);
        orderPage.clickOnMetroStationInput();
        orderPage.checkStationListIsVisible();
        orderPage.scrollToStationById(metroStationId);
        orderPage.clickMetroStation(metroStationId);
        orderPage.clickOnPhoneNumberInput(phone);
        orderPage.clickOnNextButton();

        orderPage.checkNextOrderPageIsVisible();
        orderPage.clickOnInputDelivery();
        orderPage.checkCalendarIsVisible();
        orderPage.selectDeliveryDate();
        orderPage.clickInputRentalPeriod();
        orderPage.checkRentalPeriodListIsVisible();
        orderPage.clickRentalPeriod(rentalPeriod);
        orderPage.clickScooterColorCheckBox(scooterColor);
        orderPage.clickCourierComment(comment);
        orderPage.clickOrderButton();
        orderPage.checkAcceptOrderButtonIsVisible();
        orderPage.clickAcceptOrder();
        Assert.assertTrue("Заказ не был оформлен: ", orderPage.isOrderConfirmationDisplayed());

        driver.quit();


    }
}
