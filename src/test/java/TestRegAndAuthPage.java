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

public class TestRegAndAuthPage {

    public final String linkMyAccount = "http://intershop5.skillbox.ru/my-account/";  //URL на МОЙ АККАУНТ
    public final String linkRegistration = "http://intershop5.skillbox.ru/register/";  //URL на страницу регистрации
    public final String linkLostPassword = "http://intershop5.skillbox.ru/my-account/lost-password/"; //URL на страницу восстановления пароля
    public final By postTitleLocator = By.className("post-title"); //заголовок МОЙ АККАУНТ\РЕГИСТРАЦИЯ
    public final By userNameRegLocator = By.id("reg_username");  //поле ввода ИМЯ в форме регистрации
    public final By emailRegLocator = By.id("reg_email");  //поле ввода EMAIL в форме регистрации
    public final By passwordRegLocator = By.id("reg_password");  //поле ввода ПАРОЛЯ в форме регистрации
    public final By buttonRegisterLocator = By.name("register");  //кнопка ЗАРЕГИСТРИРОВАТЬСЯ в форме регистрации
    public final By userNameAutLocator = By.id("username");  //поле ввода ИМЯ в форме авторизации
    public final By passwordAutLocator = By.id("password");  //поле ввода ПАРОЛЯ в форме авторизации
    public final By buttonAuthorizationLocator = By.name("login");  //кнопка ВОЙТИ в форме авторизации
    public final By buttonRegisterAutLocator = By.cssSelector(".lost_password button");  //кнопка ЗАРЕГИСТРИРОВАТЬСЯ в форме авторизации
    public final By messageErrorLocator = By.className("woocommerce-error"); //сообщение об ошибке ввода данных
    public final By messageErrorNameLocator = By.cssSelector(".woocommerce-error strong"); //Отображаемое имя в сообщении об ошибке ввода данных
    public final By messageNameInWelcomeLocator = By.cssSelector(".woocommerce-MyAccount-content strong");  //имя в приветственном сообщении
    public final By forgotPasswordAutLocator = By.cssSelector(".lost_password a"); //ссылка ЗАБЫЛИ ПАРОЛЬ
    public final By userLoginPasswordRecoveryLocator = By.id("user_login"); //поле ввода имени или email в форме восстановления пароля
    public final By messagePasswordRecoveryLocator = By.className("woocommerce-message"); //окно с сообщением о сбросе пароля
    public final By buttonPasswordRecoveryLocator = By.className("woocommerce-Button"); //кнопка сброса пароля
    public final String messageRegErrorName = "Error: Пожалуйста введите корректное имя пользователя.";  //сообщение о невалидном ИМЕНИ
    public final String messageRegErrorEmail = "Error: Пожалуйста, введите корректный email.";  //сообщение о невалидном Email
    public final String messageAuthNameRequired = "Error: Имя пользователя обязательно.";  //сообщение о необходимости заполнения поля ИМЯ при авторизации
    public final String messageAuthPasswordRequired = "Пароль обязателен.";  //сообщение о необходимости заполнения поля ПАРОЛЬ при авторизации
    public final String messageAuthInvalidName = "Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.";  //сообщение при авторизации о неверно введенном имени
    public final String messageAuthInvalidEmail = "Неизвестный адрес почты. Попробуйте еще раз или введите имя пользователя.";  //сообщение при авторизации о неверно введенном email
    public final String messagePasswordRecovery = "Password reset email has been sent."; //сообщение о сбросе пароля
    public final String messageEmptyDataPasswordRecovery = "Введите имя пользователя или почту.";  //сообщение о необходимости ввода данных для сброса пароля
    public final String messageInvalidDataPasswordRecovery = "Неверное имя пользователя или почта.";  //сообщение о невалидных данных при сбросе пароля

    final int delay = 10;

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

    @Test  // Тестирование процесса регистрации с незаполненными полями ввода данных
    public void regAndAuthPage_Registration_EmptyData() {

        driver.navigate().to(linkRegistration);

        driver.findElement(buttonRegisterLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о необходимости заполнить поля",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения об ошибке не соответствует действительности",
                messageRegErrorEmail, actualResult);

    }

    @Test  // Тестирование процесса регистрации с незаполненным полем ИМЯ
    public void regAndAuthPage_Registration_EmptyName() {

        driver.navigate().to(linkRegistration);

        driver.findElement(emailRegLocator).sendKeys("123@111.ru");
        driver.findElement(passwordRegLocator).sendKeys("111");
        driver.findElement(buttonRegisterLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о необходимости заполнить поле ИМЯ",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения об ошибке не соответствует действительности",
                messageRegErrorName, actualResult);

    }

    @Test  // Тестирование процесса регистрации с незаполненным полем Email
    public void regAndAuthPage_Registration_EmptyEmail() {

        driver.navigate().to(linkRegistration);

        driver.findElement(userNameRegLocator).sendKeys("111");
        driver.findElement(passwordRegLocator).sendKeys("111");
        driver.findElement(buttonRegisterLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о необходимости заполнить поле EMAIL",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения об ошибке не соответствует действительности",
                messageRegErrorEmail, actualResult);

    }

    @Test  // Тестирование процесса перехода к форме регистрации из формы авторизации
    public void regAndAuthPage_Authorization_GoRegistrationForm() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(buttonRegisterAutLocator).click();
        Assert.assertEquals("Не осуществлен переход к форме регистрации",
                linkRegistration, driver.getCurrentUrl());

    }

    @Test  // Тестирование процесса авторизации по имени
    public void regAndAuthPage_AuthorizationByName_SuccessfulAuthorization() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(userNameAutLocator).sendKeys("111");
        driver.findElement(passwordAutLocator).sendKeys("111");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageNameInWelcomeLocator));
        var actualResult = driver.findElement(messageNameInWelcomeLocator).getText();

        Assert.assertTrue("В шапке отсутствует приветственное сообщение",
                driver.findElement(messageNameInWelcomeLocator).isDisplayed());
        Assert.assertEquals("Имя, в приветственном сообщении не соответствует введенному при авторизации",
                "111", actualResult);

    }

    @Test  // Тестирование процесса авторизации по EMAIL
    public void regAndAuthPage_AuthorizationByEmail_SuccessfulAuthorization() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(userNameAutLocator).sendKeys("111@111.ru");
        driver.findElement(passwordAutLocator).sendKeys("111");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageNameInWelcomeLocator));
        var actualResult = driver.findElement(messageNameInWelcomeLocator).getText();

        Assert.assertTrue("В шапке отсутствует приветственное сообщение",
                driver.findElement(messageNameInWelcomeLocator).isDisplayed());
        Assert.assertEquals("Имя, в приветственном сообщении не соответствует введенному при регистрации",
                "111", actualResult);

    }

    @Test  // Тестирование процесса авторизации с пустыми полями
    public void regAndAuthPage_Authorization_EmptyData() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о необходимости заполнить поля для авторизации",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageAuthNameRequired, actualResult);

    }

    @Test  // Тестирование процесса авторизации с пустым полем ИМЯ
    public void regAndAuthPage_Authorization_EmptyName() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(passwordAutLocator).sendKeys("111");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о необходимости заполнить поле ИМЯ для авторизации",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageAuthNameRequired, actualResult);

    }

    @Test  // Тестирование процесса авторизации с пустым полем ПАРОЛЬ
    public void regAndAuthPage_Authorization_EmptyPassword() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(userNameAutLocator).sendKeys("111");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о необходимости заполнить поле ПАРОЛЬ для авторизации",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageAuthPasswordRequired, actualResult);

    }

    @Test  // Тестирование процесса авторизации с неверным именем
    public void regAndAuthPage_Authorization_InvalidName() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(userNameAutLocator).sendKeys("222");
        driver.findElement(passwordAutLocator).sendKeys("111");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о неверном имени",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageAuthInvalidName, actualResult);

    }

    @Test  // Тестирование процесса авторизации с неверным email
    public void regAndAuthPage_Authorization_InvalidEmail() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(userNameAutLocator).sendKeys("222@222.ru");
        driver.findElement(passwordAutLocator).sendKeys("111");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о неверном имени",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageAuthInvalidEmail, actualResult);

    }

    @Test  // Тестирование процесса авторизации с неверным паролем
    public void regAndAuthPage_Authorization_InvalidPassword() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(userNameAutLocator).sendKeys("111");
        //var expectedResult = driver.findElement(locators.userNameAutLocator).getText();
        driver.findElement(passwordAutLocator).sendKeys("222");
        driver.findElement(buttonAuthorizationLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorNameLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о неверном имени",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                "111", actualResult);

    }

    @Test  // Тестирование процесса перехода к форме восстановления пароля
    public void regAndAuthPage_Authorization_FormPasswordRecovery() {

        driver.navigate().to(linkMyAccount);

        driver.findElement(forgotPasswordAutLocator).click();
        var actualResult = driver.findElement(postTitleLocator).getText();

        Assert.assertEquals("Не осуществлен переход к форме восстановления пароля",
                linkLostPassword, driver.getCurrentUrl());
        Assert.assertEquals("Не отобразилась форма восстановления пароля",
                actualResult, "Восстановление пароля");

    }

    @Test  // Тестирование процесса сброса пароля
    public void regAndAuthPagePage_PasswordRecovery() {

        driver.navigate().to(linkLostPassword);

        driver.findElement(userLoginPasswordRecoveryLocator).sendKeys("111");
        driver.findElement(buttonPasswordRecoveryLocator).click();
        var actualResult = driver.findElement(messagePasswordRecoveryLocator).getText();

        Assert.assertTrue("Не отобразилось сообщение о сбросе пароля",
                driver.findElement(messagePasswordRecoveryLocator).isDisplayed());
        Assert.assertEquals("Сообщение о сбросе пароля не соответствует действительности",
                messagePasswordRecovery, actualResult);

    }

    @Test  // Тестирование процесса сброса пароля без введения имени или email
    public void regAndAuthPage_PasswordRecovery_EmptyData() {

        driver.navigate().to(linkLostPassword);

        driver.findElement(buttonPasswordRecoveryLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о пустом поле",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageEmptyDataPasswordRecovery, actualResult);

    }

    @Test  // Тестирование процесса сброса пароля при вводе невалидных данных
    public void regAndAuthPage_PasswordRecovery_InvalidData() {

        driver.navigate().to(linkLostPassword);

        driver.findElement(userLoginPasswordRecoveryLocator).sendKeys("222");
        driver.findElement(buttonPasswordRecoveryLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        var actualResult = driver.findElement(messageErrorLocator).getText();

        Assert.assertTrue("Отсутствует сообщение о неверном имени",
                driver.findElement(messageErrorLocator).isDisplayed());
        Assert.assertEquals("Текст сообщения не соответствует действительности",
                messageInvalidDataPasswordRecovery, actualResult);

    }

}
