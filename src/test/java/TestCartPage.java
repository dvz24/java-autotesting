import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestCartPage {

    private final By enterButton = By.cssSelector("a[class='account']");
    private final By fieldName = By.cssSelector("[id='username']");
    private final By fieldPassword = By.cssSelector("[id='password']");
    private final By buttonEnter = By.cssSelector("[class^='woocommerce-button']");
    private final By buttonCatalog = By.xpath("//*[text()='Каталог']");
    private final By searchOfProduct = By.cssSelector("[data-product_id='15']");
    private final By buttonBasketApple = By.xpath("(//*[text()='Корзина'])[1]");
    private final By buttonBasket = By.xpath("//*[@id='menu-item-29']//a");
    private final By badgeDeleteFromBasket = By.cssSelector(".product-remove a");
    private final By nameAppleWatch = By.cssSelector(".content-page .woocommerce .product-name :nth-of-type(1)");
    private final By buttonReturn = By.cssSelector(".woocommerce-notices-wrapper a");
    private final By fieldCertificate = By.cssSelector("*[id=coupon_code]");
    private final By buttonUseCoupon = By.cssSelector("[name=apply_coupon]");
    private final By elementDiscount = By.cssSelector(".cart-discount.coupon-sert500");
    final int delay = 10;


    final String name = "Will Smith";
    final String password = "1";

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
        driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test  //  Тестирование процедуры удаления товара из корзины
    public void cartPage_SaleProduct_removeFromBasket() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(enterButton).click();
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonEnter).click();

        driver.findElement(buttonCatalog).click();
        driver.findElement(searchOfProduct).click();
        driver.findElement(buttonBasketApple).click();
        driver.findElement(buttonBasket).click();
        driver.findElement(badgeDeleteFromBasket).click();

        Assert.assertTrue("товар до сих пор в корзине", driver.findElement(nameAppleWatch).isDisplayed());

    }

    @Test  //  Тестирование возврата удаленного товара из корзины
    public void cartPage_ReturnProduct_afterDelete() {

        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(enterButton).click();
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonEnter).click();
        driver.findElement(buttonCatalog).click();
        driver.findElement(searchOfProduct).click();
        driver.findElement(buttonBasketApple).click();
        driver.findElement(buttonBasket).click();
        driver.findElement(badgeDeleteFromBasket).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonReturn));
        driver.findElement(buttonReturn).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(nameAppleWatch));

        Assert.assertTrue("товар не возвращен", driver.findElement(nameAppleWatch).isDisplayed());

    }

    @Test  //  Тестирование варианта покупки с применением скидочного купона
    public void cartPage_SaleProduct_useCertificate() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(enterButton).click();
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonEnter).click();

        driver.findElement(buttonCatalog).click();
        driver.findElement(searchOfProduct).click();
        driver.findElement(buttonBasketApple).click();
        driver.findElement(buttonBasket).click();
        driver.findElement(fieldCertificate).click();
        driver.findElement(fieldCertificate).sendKeys("sert500");
        driver.findElement(buttonUseCoupon).click();

        Assert.assertTrue("не отображается скидка", driver.findElement(elementDiscount).isDisplayed());

    }

}
