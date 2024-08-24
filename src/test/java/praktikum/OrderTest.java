package praktikum;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;

public class OrderTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.get(EnvConfig.BASE_URL);
    }

    @Test
    public void oderScooter() throws Exception {
        var orderPage = new OrderPage(driver);
        var mainPain = new MainPage(driver);
        mainPain.clickOrderButton();
        orderPage.checkOrderHeaderIsVisible();
        orderPage.clickOnInputName("Sasha");
        orderPage.clickOnInputSurname("SSS");
        orderPage.clickOnInputAddress("Moscow");
        orderPage.clickOnMetroStationInput();
        orderPage.checkStationListIsVisible();
        orderPage.scrollToStationById("6");
        orderPage.clickMetroStation("6");
        orderPage.clickOnPhoneNumberInput("87001112");
    }
}
