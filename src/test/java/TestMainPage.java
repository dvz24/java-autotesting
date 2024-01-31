import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestMainPage {

    //  Локаторы основных страниц сайта
    private final By mainPageLocator = By.className("home");                          //  главная страница
    private final By catalogPageLocator = By.cssSelector(".content-area .clearfix");  //  страница КАТАЛОГ
    private final By accountPageLocator = By.id("post-22");                           //  страница МОЙ АККАУНТ
    private final By cartPageLocator = By.id("post-20");                              //  страница КОРЗИНА
    private final By checkoutPageLocator = By.id("post-24");                          //  страница ОФОРМЛЕНИЕ ЗАКАЗА
    private final By registerPageLocator = By.id("post-141");                         //  страница РЕГИСТРАЦИЯ
    //  Тестирование наличия на главной странице семи основных функциональных блоков
    private final By headerLocator = By.id("mastheads");                   //  локатор хэдера
    private final By promoCategoryLocator = By.id("promo-section1");       //  локатор блока с промо-виджетами
    private final By saleLocator = By.id("product1");                      //  локатор блока РАСПРОДАЖА
    private final By onSaleNowLocator = By.id("promo-section2");           //  локатор блока УЖЕ В ПРОДАЖЕ
    private final By newArrivalsLocator = By.id("product2");               //  локатор блока НОВЫЕ ПОСТУПЛЕНИЯ
    private final By viewedProductsLocator = By.className("ap-cat-list");  //  локатор блока ПРОСМОТРЕННЫЕ ТОВАРЫ
    private final By footerLocator = By.id("colophon");                    //  локатор футера
    //  Тестирование функциональности кнопок панели навигации хэдера
    private final By mainButton = By.id("menu-item-26");      //  надпись-ссылка ГЛАВНАЯ
    private final By catalogButton = By.id("menu-item-46");   //  надпись-ссылка КАТАЛОГ
    private final By accountButton = By.id("menu-item-30");   //  надпись-ссылка МОЙ АККАУНТ
    private final By cartButton = By.id("menu-item-29");      //  надпись-ссылка КОРЗИНА
    private final By checkoutButton = By.id("menu-item-31");  //  надпись-ссылка ОФОРМЛЕНИЕ ЗАКАЗА
    //  Тестирование функциональности перехода из раздела РАСПРОДАЖА в подраздел каталога, в корзину, и обратно на главную
    private final By fotoSaleProductLocator = By.cssSelector("#accesspress_store_product-2 > ul > div > div > li:nth-child(5) > div > a:nth-child(1)");
    private final By readMoreButtonLocator = By.cssSelector("#accesspress_store_product-2 > ul > div > div > li.span3.wow.flipInY.slick-slide.slick-active > div > a.button.product_type_simple");
    private final By addCartButtonLocator = By.cssSelector("#accesspress_store_product-2 > ul > div > div > li.span3.wow.flipInY.slick-slide.slick-active > div > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart\n");
    private final By moreButtonLocator = By.cssSelector("#accesspress_store_product-2 > ul > div > div > li.span3.wow.flipInY.slick-slide.slick-active > div > a.added_to_cart.wc-forward");
    //  Тестирование функциональности раздела УЖЕ В ПРОДАЖЕ
    private final By fotoOnSaleNowProductLocator = By.cssSelector("#accesspress_store_full_promo-2 a"); // баннер промо-блока УЖЕ В ПРОДАЖЕ
    private final By viewProductButtonLocator = By.cssSelector("#accesspress_store_full_promo-2 span"); // кнопка ПРОСМОТРЕТЬ ТОВАР
    private final By productNameInBannerLocator = By.className("promo-desc-title");                     // имя промо-товара в блоке УЖЕ В ПРОДАЖЕ
    private final By productNameInCardLocator = By.className("product_title");                          // имя товара в карточке каталога
    //  Тестирование функциональности перехода из раздела НОВЫЕ ПОСТУПЛЕНИЯ в подраздел каталога, в корзину, и обратно на главную
    private final By fotoNewProductLocator = By.cssSelector("#accesspress_store_product-3 > ul > div > div > li:nth-child(5) > div > a");
    private final By readMoreNewButtonLocator = By.cssSelector("#accesspress_store_product-3 > ul > div > div > li:nth-child(5) > div > a.button.product_type_simple");
    private final By addCartNewButtonLocator = By.cssSelector("#accesspress_store_product-3 > ul > div > div > li:nth-child(8) > div > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart");
    private final By moreNewButtonLocator = By.cssSelector("#accesspress_store_product-3 > ul > div > div > li:nth-child(8) > div > a.added_to_cart.wc-forward");
    //  Тестирование функциональности раздела ПРОСМОТРЕННЫЕ ТОВАРЫ
    private final By firstViewedProductLocator = By.cssSelector("#accesspress_store_full_promo-2 > div > a > div > div > span");
    private final By secondViewedProductLocator = By.cssSelector("#accesspress_store_product-2 > ul > div > div > li:nth-child(5) > div > a.button.product_type_simple");
    private final By linkTopViewedProductLocator = By.cssSelector("#woocommerce_recently_viewed_products-2 > ul > li:nth-child(1) > a");
    //  Тестирование функциональности ссылок в футере
    private final By linkAllProductsInFooterLocator = By.cssSelector("#pages-2 > ul > li.page_item.page-item-33 > a"); // локатор ссылки `Все товары`
    private final By linkMainPageInFooterLocator = By.cssSelector("#pages-2 > ul > li.page_item.page-item-39.current_page_item > a"); //локатор ссылки `Главная`
    private final By linkCartInFooterLocator = By.cssSelector("#pages-2 > ul > li.page_item.page-item-20 > a"); //локатор ссылки `Корзина`
    private final By linkMyAccountInFooterLocator = By.cssSelector("#pages-2 > ul > li.page_item.page-item-22 > a"); //локатор ссылки `Мой аккаунт`
    private final By linkCheckoutInFooterLocator = By.cssSelector("#pages-2 > ul > li.page_item.page-item-24 > a"); //локатор ссылки `Оформление заказа`
    private final By linkRegisterInFooterLocator = By.cssSelector("#pages-2 > ul > li.page_item.page-item-141 > a"); //локатор ссылки `Регистрация`
    //  Тестирование функциональности ссылок для перехода в каталог по нажатию на промо-виджеты и обратно на главную
    public final By firstWidgetLocator = By.id("accesspress_storemo-2");  // первый промо-виджет
    public final By middleWidgetLocator = By.id("accesspress_storemo-3"); // средний промо-виджет
    public final By lastWidgetLocator = By.id("accesspress_storemo-4");   // последний промо-виджет
    final int delay = 18;
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

    @Test
    public void mainPage_PageContent_AllBlocksPresent() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        Assert.assertTrue("Отсутствует хэдер",
                driver.findElement(headerLocator).isDisplayed());
        Assert.assertTrue("Отсутствует блок с промо-виджетами",
                driver.findElement(promoCategoryLocator).isDisplayed());
        Assert.assertTrue("Отсутствует блок РАСПРОДАЖА",
                driver.findElement(saleLocator).isDisplayed());
        Assert.assertTrue("Отсутствует блок УЖЕ В ПРОДАЖЕ",
                driver.findElement(onSaleNowLocator).isDisplayed());
        Assert.assertTrue("Отсутствует блок НОВЫЕ ПОСТУПЛЕНИЯ",
                driver.findElement(newArrivalsLocator).isDisplayed());
        Assert.assertTrue("Отсутствует блок ПРОСМОТРЕННЫЕ ТОВАРЫ",
                driver.findElement(viewedProductsLocator).isDisplayed());
        Assert.assertTrue("Отсутствует футер",
                driver.findElement(footerLocator).isDisplayed());

    }

    @Test
    public void mainPage_HeaderNavMenu_AllLinksWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        driver.findElement(mainButton).click();
        Assert.assertTrue("Главная страница не открылась из меню навигации!",
                driver.findElement(mainPageLocator).isDisplayed());
        driver.findElement(catalogButton).click();
        Assert.assertTrue("Страница `Каталог` не открылась из меню навигации!",
                driver.findElement(catalogPageLocator).isDisplayed());
        driver.findElement(accountButton).click();
        Assert.assertTrue("Страница `Мой аккаунт` не открылась из меню навигации!",
                driver.findElement(accountPageLocator).isDisplayed());
        driver.findElement(cartButton).click();
        Assert.assertTrue("Страница `Корзина` не открылась из меню навигации!",
                driver.findElement(cartPageLocator).isDisplayed());
        // проверка перехода на страницу `Оформление заказа` при отсутствии товара в корзине
        driver.findElement(checkoutButton).click();
        Assert.assertTrue("Страница `Оформление заказа` открылась при отсутствии товара в корзине!",
                driver.findElement(cartPageLocator).isDisplayed());
        // добавление товара из каталога в корзину для корректной проверки страницы `Оформление заказа`
        driver.findElement(catalogButton).click();
        driver.findElement(By.cssSelector(".ajax_add_to_cart")).click();
        driver.findElement(mainButton).click();
        driver.findElement(checkoutButton).click();
        Assert.assertTrue("Страница `Оформление заказа` не открылась из меню навигации!",
                driver.findElement(checkoutPageLocator).isDisplayed());

    }

    @Test
    public void mainPage_PromoWidgets_AllLinksWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        var linkFirstWidget = driver.findElement(By.cssSelector("#accesspress_storemo-2 a")).getAttribute("href");
        driver.findElement(firstWidgetLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки!",
                linkFirstWidget, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        var linkMiddleWidget = driver.findElement(By.cssSelector("#accesspress_storemo-3 a")).getAttribute("href");
        driver.findElement(middleWidgetLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки!",
                linkMiddleWidget, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        var linkLostWidget = driver.findElement(By.cssSelector("#accesspress_storemo-4 a")).getAttribute("href");
        driver.findElement(lastWidgetLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки!",
                linkLostWidget, driver.getCurrentUrl());
        driver.findElement(mainButton).click();
    }

    @Test
    public void mainPage_SaleSectionFunctions_AllFuncsWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        // Кликнуть фото товара из слайдера распродажи, перейти на страницу товара и обратно на главную
        var linkSaleProduct = driver.findElement(fotoSaleProductLocator).getAttribute("href");
        driver.findElement(fotoSaleProductLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки",
                linkSaleProduct, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        // Кликнуть кнопку Read more, перейти в подраздел каталога и обратно на главную
        var linkReadMoreButton = driver.findElement(readMoreButtonLocator).getAttribute("href");
        driver.findElement(readMoreButtonLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки",
                linkReadMoreButton, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        // Кликнуть кнопку В КОРЗИНУ, кликнуть кнопку ПОДРОБНЕЕ, перейти в корзину и обратно на главную
        driver.findElement(addCartButtonLocator).click();
        driver.findElement(moreButtonLocator).click();
        Assert.assertTrue("Страница `Корзина` не открылась!",
                driver.findElement(cartPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

    }

    @Test
    public void mainPage_OnSaleNowSectionFunctions_AllFuncsWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        var linkOnSaleNowProduct = driver.findElement(fotoOnSaleNowProductLocator).getAttribute("href");

        // проверка кликабельности баннера и ссылки на нужный товар
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
        driver.findElement(fotoOnSaleNowProductLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки!",
                linkOnSaleNowProduct, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        // проверка кнопки ПРОСМОТРЕТЬ ТОВАР и ссылки на нужный товар
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
        driver.findElement(viewProductButtonLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки!",
                linkOnSaleNowProduct, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        // проверка соответствия названия товара на промо-баннере с названием товара на открывшейся странице каталога
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNameInBannerLocator));
        var productNameInBanner = driver.findElement(productNameInBannerLocator).getText().toLowerCase();
        driver.findElement(fotoOnSaleNowProductLocator).click();
        var productNameInCard = driver.findElement(productNameInCardLocator).getText().toLowerCase();
        Assert.assertEquals("Название товара на баннере не совпадает с названием товара в карточке из каталога!",
                productNameInBanner, productNameInCard);

    }

    @Test
    public void mainPage_NewArrivalsSectionFunctions_AllFuncsWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        // Кликнуть фото товара из слайдера распродажи, перейти на страницу товара и обратно на главную
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN);
        var linkSaleProduct = driver.findElement(fotoNewProductLocator).getAttribute("href");
        driver.findElement(fotoNewProductLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки",
                linkSaleProduct, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        // Кликнуть кнопку Read more, перейти в подраздел каталога и обратно на главную
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN);
        var linkReadMoreButton = driver.findElement(readMoreNewButtonLocator).getAttribute("href");
        driver.findElement(readMoreNewButtonLocator).click();
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки",
                linkReadMoreButton, driver.getCurrentUrl());
        driver.findElement(mainButton).click();

        // Кликнуть кнопку В КОРЗИНУ, кликнуть кнопку ПОДРОБНЕЕ, перейти в корзину и обратно на главную
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN);
        driver.findElement(addCartNewButtonLocator).click();
        driver.findElement(moreNewButtonLocator).click();
        Assert.assertTrue("Страница `Корзина` не открылась!",
                driver.findElement(cartPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

    }

    @Test
    public void mainPage_ViewedProductsSectionFunctions_AllFuncsWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        // просмотр товара А
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
        driver.findElement(firstViewedProductLocator).click();
        driver.findElement(mainButton).click();

        // просмотр товара Б
        var linkSecondViewedProduct = driver.findElement(secondViewedProductLocator).getAttribute("href");
        driver.findElement(secondViewedProductLocator).click();
        driver.findElement(mainButton).click();

        // просмотр списка товаров, где товар Б должен быть расположен над товаром А
        var linkTopViewedProduct = driver.findElement(linkTopViewedProductLocator).getAttribute("href");
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки!",
                linkTopViewedProduct, linkSecondViewedProduct);

    }

    @Test
    public void mainPage_FooterSection_AllLinksWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/");

        //  Проверка перехода по ссылке из футера на страницу `Все товары`
        driver.findElement(linkAllProductsInFooterLocator).click();
        var textTitleAllProducts =
                driver.findElement(By.cssSelector("#title_bread_wrap > div > div > span")).getText().toLowerCase();
        var textTitleAllProductsInFooter =
                driver.findElement(By.cssSelector(".page-item-33 a")).getText().toLowerCase();
        Assert.assertEquals("По ссылке из футера не открылась страница `Все товары`!",
                textTitleAllProducts, textTitleAllProductsInFooter);
        driver.findElement(mainButton).click();

        //  Проверка перехода по ссылке из футера на `Главную страницу`
        driver.findElement(linkMainPageInFooterLocator).click();
        Assert.assertTrue("По ссылке из футера не открылась `Главная страница`!",
                driver.findElement(mainPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

        //  Проверка перехода по ссылке из футера на страницу `Корзина`
        driver.findElement(linkCartInFooterLocator).click();
        Assert.assertTrue("По ссылке из футера не открылась страница `Корзина`!",
                driver.findElement(cartPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

        //  Проверка перехода по ссылке из футера на страницу `Мой аккаунт`
        driver.findElement(linkMyAccountInFooterLocator).click();
        Assert.assertTrue("По ссылке из футера не открылась страница `Мой аккаунт`!",
                driver.findElement(accountPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

        //  Проверка работы ссылки `Оформление заказа` в футере. Предусловие: без товара в корзине.
        driver.findElement(linkCheckoutInFooterLocator).click();
        Assert.assertTrue("По ссылке из футера не открылась страница `Оформление заказа`!",
                driver.findElement(cartPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

        // Проверка перехода по ссылке из футера на страницу `Оформление заказа`. Предусловие: добавить товар в корзину.
        driver.findElement(catalogButton).click();
        driver.findElement(By.cssSelector(".ajax_add_to_cart")).click();
        driver.findElement(mainButton).click();
        driver.findElement(linkCheckoutInFooterLocator).click();
        Assert.assertTrue("По ссылке из футера не открылась страница `Оформление заказа`!",
                driver.findElement(checkoutPageLocator).isDisplayed());
        driver.findElement(mainButton).click();

        //  Проверка перехода по ссылке из футера на страницу `Регистрация`
        driver.findElement(linkRegisterInFooterLocator).click();
        Assert.assertTrue("По ссылке из футера не открылась страница `Регистрация`!",
                driver.findElement(registerPageLocator).isDisplayed());

    }

}
