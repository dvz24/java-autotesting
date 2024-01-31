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

public class TestCheckoutPage {

    private final By catalogButtonHeader = By.cssSelector(".menu-item-46 a");
    private final By orderingButtonHeader = By.cssSelector(".menu-item-31 a");
    private final By cartButtonHeader = By.cssSelector(".menu-item-29 a");
    private final By myAccountButtonHeader = By.cssSelector(".menu-item-30 a");
    private final By pleaseLogIn = By.cssSelector(".showlogin");
    private final By checkoutButton = By.cssSelector(".checkout-button");
    private final By logOutButton = By.linkText("Выйти");
    private final By orderButton = By.id("place_order");
    private final By firstProductAddToCartButton = By.cssSelector(".product:nth-child(3) .button");
    private final By firstProductAddedToCart = By.cssSelector(".post-15 .added_to_cart");
    private final By secondProductAddToCartButton = By.cssSelector(".product:nth-child(5) .button");
    private final By secondProductAddedToCart = By.cssSelector(".post-53 .added_to_cart");
    private final By thirdProductAddToCartButton = By.cssSelector(".product:nth-child(12) .button");
    private final By thirdProductAddedToCart = By.cssSelector(".post-79 .added_to_cart");
    private final By userNameInputAuthForm = By.id("username");
    private final By passwordInputAuthForm = By.id("password");
    private final By logInButtonAuthForm = By.cssSelector(".woocommerce-button");
    private final By billingFirstName = By.id("billing_first_name");
    private final By billingLastName = By.id("billing_last_name");
    private final By billingCountry = By.id("select2-billing_country-container");
    private final By selectSerbia = By.cssSelector("[value='RS']");
    private final By selectedCountry = By.id("select2-billing_country-container");
    private final By billingAddress = By.id("billing_address_1");
    private final By billingCity = By.id("billing_city");
    private final By billingState = By.id("billing_state");
    private final By billingPostCode = By.id("billing_postcode");
    private final By billingPhone = By.id("billing_phone");
    private final By billingEmail = By.id("billing_email");
    private final By notFilledFirstNameMessage = By.cssSelector("[data-id='billing_first_name'] strong");
    private final By notFilledLastNameMessage = By.cssSelector("[data-id='billing_last_name'] strong");
    private final By notFilledAddressMessage = By.cssSelector("[data-id='billing_address_1'] strong");
    private final By notFilledCityMessage = By.cssSelector("[data-id='billing_city'] strong");
    private final By notFilledStateMessage = By.cssSelector("[data-id='billing_state'] strong");
    private final By notFilledPostcodeMessage = By.cssSelector("[data-id='billing_postcode'] strong");
    private final By notFilledPhoneMessage = By.cssSelector("[data-id='billing_phone']:nth-of-type(2) strong");
    private final By notFilledEmailMessage = By.cssSelector("[data-id='billing_email'] strong");
    private final By payCash = By.id("payment_method_cod");
    private final By payBank = By.id("payment_method_bacs");
    private final By haveDiscountOrderPage = By.cssSelector("a.showcoupon");
    private final By couponFormOrderPage = By.cssSelector(".woocommerce-form-coupon");
    private final By couponInputOrderPage = By.cssSelector("[name='coupon_code']");
    private final By applyCouponButtonOrderPage = By.cssSelector("[name='apply_coupon']");
    private final By couponWasApplied = By.cssSelector(".coupon-sert500 th");
    private final By paymentMethod = By.cssSelector(".woocommerce-order-overview__payment-method strong");
    private final By orderReceivedMessage = By.xpath("//h2[contains(text(),'Заказ получен')]");
    private final By couponMessage = By.cssSelector(".woocommerce-message");
    final int delay = 12;
    final String userName = "Will Smith";
    final String password = "1";
    final String name = "Will";
    final String lastName = "Smith";
    final String address = "148th Avenue NE Suite 201";
    final String city = "Olympia";
    final String state = "Washington";
    final String postCode = "123456";
    final String phone = "0-425-391-1997";
    final String email = "7@mail.ru";
    final String coupon = "sert500";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
        driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);

    }

    @After
    public void teardown() {

        driver.quit();

    }

    @Test  // Тестирование процесса оформления заказа: заполнение всех полей, безналичный расчет
    public void checkoutPage_MakeOrderWithoutLoginWithAllFieldsBankPayment_OrderComplete() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(secondProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        driver.findElement(thirdProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен",
                driver.findElement(orderReceivedMessage)
                        .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты",
                driver.findElement(paymentMethod)
                        .getText().contains("Прямой банковский перевод"));

    }

    @Test  // Тестирование процесса оформления заказа: заполнение всех полей, наличный расчет
    public void checkoutPage_MakeOrderWithoutLoginWithAllFieldsCashPayment_OrderComplete() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(secondProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        driver.findElement(thirdProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(payCash).click();
        driver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен",
                driver.findElement(orderReceivedMessage)
                        .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты",
                driver.findElement(paymentMethod)
                        .getText().contains("Оплата при доставке"));

    }

    @Test  //  Тестирование процесса оформления заказа: авторизация на сайте, заполнение всех полей, безналичный расчет
    public void checkoutPage_LoggedInUserMakesOrderWithAllFieldsBankPayment_OrderComplete() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(myAccountButtonHeader).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(secondProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        driver.findElement(thirdProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingState));
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен", driver.findElement(orderReceivedMessage)
                .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты", driver.findElement(paymentMethod)
                .getText().contains("Прямой банковский перевод"));

    }

    @Test  //  Тестирование процесса оформления заказа: авторизация на сайте, заполнение всех полей, наличный расчет
    public void checkoutPage_LoggedInUserMakesOrderWithAllFieldsCashPayment_OrderComplete() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(myAccountButtonHeader).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(secondProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        driver.findElement(thirdProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(payCash).click();
        driver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен",
                driver.findElement(orderReceivedMessage)
                        .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты",
                driver.findElement(paymentMethod)
                        .getText().contains("Оплата при доставке"));

    }

    @Test
    //  Тестирование процесса оформления заказа из корзины: авторизованный пользователь, заполнение всех полей, безналичный расчет
    public void checkoutPage_LoggedInUserMakesOrderWithAllFieldsViaCartPage_OrderComplete() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(myAccountButtonHeader).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(secondProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        driver.findElement(thirdProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        driver.findElement(cartButtonHeader).click();
        driver.findElement(checkoutButton).click();
        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(payBank).click();
        driver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен",
                driver.findElement(orderReceivedMessage)
                        .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты",
                driver.findElement(paymentMethod)
                        .getText().contains("Прямой банковский перевод"));

    }

    @Test  //  Тестирование процесса оформления заказа: авторизованный пользователь, применение купона
    public void checkoutPage_CheckoutWithDiscountCoupon_DiscountApplied() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(myAccountButtonHeader).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(secondProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        driver.findElement(thirdProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(haveDiscountOrderPage).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponFormOrderPage));
        driver.findElement(couponInputOrderPage).sendKeys(coupon);
        driver.findElement(applyCouponButtonOrderPage).click();

        Assert.assertTrue("не появилось сообщение о добавлении купона вместо поля ввода купона",
                driver.findElement(couponMessage)
                        .getText().contains("Купон успешно добавлен."));

        Assert.assertTrue("купон sert500 не отобразился в общей стоимости заказа",
                driver.findElement(couponWasApplied)
                        .getText().contains("СКИДКА: SERT500"));
        driver.findElement(logOutButton).click();

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме имени пользователя
    public void checkoutPage_MakeOrderWithoutUserFirstNameField_FirstNameRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(billingFirstName).clear();
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном имени",
                driver.findElement(notFilledFirstNameMessage)
                        .getText().contains("Имя для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме фамилии пользователя
    public void checkoutPage_MakeOrderWithoutUserLastNameField_LastNameRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();
        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненной фамилии",
                driver.findElement(notFilledLastNameMessage)
                        .getText().contains("Фамилия для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме адреса пользователя
    public void checkoutPage_MakeOrderWithoutAddressField_AddressRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном адресе",
                driver.findElement(notFilledAddressMessage)
                        .getText().contains("Адрес для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме города пользователя
    public void checkoutPage_MakeOrderWithoutCityField_CityRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном городе",
                driver.findElement(notFilledCityMessage)
                        .getText().contains("Город / Населенный пункт для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме региона пользователя
    public void checkoutPage_MakeOrderWithoutStateField_StateRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненной области",
                driver.findElement(notFilledStateMessage)
                        .getText().contains("Область для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме почтового индекса пользователя
    public void checkoutPage_MakeOrderWithoutPostCodeField_PostCodeRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);

        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном индексе", driver.findElement(notFilledPostcodeMessage)
                .getText().contains("Почтовый индекс для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме телефона пользователя
    public void checkoutPage_MakeOrderWithoutPhoneField_PhoneRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном номере телефона", driver.findElement(notFilledPhoneMessage)
                .getText().contains("Телефон для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей, кроме e-mail пользователя
    public void checkoutPage_MakeOrderWithoutEmailField_EmailRequiredMessage() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingFirstName).clear();
        driver.findElement(billingFirstName).sendKeys(name);
        driver.findElement(billingLastName).clear();
        driver.findElement(billingLastName).sendKeys(lastName);
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingState).clear();
        driver.findElement(billingState).sendKeys(state);
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).clear();
        driver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном email", driver.findElement(notFilledEmailMessage)
                .getText().contains("Адрес почты для выставления счета"));

    }

    @Test  //  Тестирование процесса оформления заказа: заполнение всех полей с указанием страны Serbia
    public void checkoutPage_SelectCountryFromListSerbia_SelectedCountrySerbia() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(catalogButtonHeader).click();
        driver.findElement(firstProductAddToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        driver.findElement(orderingButtonHeader).click();
        driver.findElement(pleaseLogIn).click();
        driver.findElement(userNameInputAuthForm).sendKeys(userName);
        driver.findElement(passwordInputAuthForm).sendKeys(password);
        driver.findElement(logInButtonAuthForm).click();

        driver.findElement(billingCountry).click();
        driver.findElement(selectSerbia).click();

        Assert.assertTrue("Не выбрана страна Serbia!", driver.findElement(selectedCountry)
                .getText().contains("Serbia"));

    }

}
