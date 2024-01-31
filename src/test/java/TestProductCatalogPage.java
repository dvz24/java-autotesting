import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class TestProductCatalogPage {
    //  Тестирование функциональности ссылок бокового субменю для перехода на нужную страницу (по валидности ссылки и точному заголовку страницы)
    private final By uncategorizedLocator = By.cssSelector(".cat-item-15 a");
    private final By appliancesLocator = By.cssSelector(".cat-item-20 a");
    private final By catalogLocator = By.cssSelector(".cat-item-19 a");
    private final By booksLocator = By.cssSelector(".cat-item-28 a");
    private final By clothesLocator = By.cssSelector(".cat-item-17 a");
    private final By padLocator = By.cssSelector(".cat-item-26 a");
    private final By washLocator = By.cssSelector(".cat-item-22 a");
    private final By tvLocator = By.cssSelector(".cat-item-25 a");
    private final By phonesLocator = By.cssSelector(".cat-item-24 a");
    private final By photoVideoLocator = By.cssSelector(".cat-item-27 a");
    private final By refrigeratorsLocator = By.cssSelector(".cat-item-21 a");
    private final By watchLocator = By.cssSelector(".cat-item-23 a");
    private final By electronicsLocator = By.cssSelector(".cat-item-16 a");
    private final By pageTitle = By.cssSelector("#title_bread_wrap h1");
    //  Тестирование функциональности добавления товаров из каталога в корзину
    private final By catalogButton = By.id("menu-item-46");   //  надпись-ссылка КАТАЛОГ в хэдере
    private final By linkHouseholdEquipmentCatalogListLocator = By.linkText("Бытовая техника");
    private final By addBasketButton = By.cssSelector(".product:nth-child(1) .button");
    private final By moreInformationButtonLocator = By.cssSelector(".added_to_cart");
    private final By expectedHeaderResultLocator = By.linkText("Стиральная машина BEKO WRE64P1BWW, фронтальная, 6кг, 800об/мин");
    private final By addRefrigeratorBasketButton = By.cssSelector(".product:nth-child(10) .button");
    private final By refrigeratorExpectedHeaderResultLocator = By.linkText("Холодильник БИРЮСА Б-M10, однокамерный, серебристый");
    private final By tablesButtonLocator = By.linkText("Планшеты");
    private final By tablesCardAddButtonLocator = By.cssSelector(".product:nth-child(1) .button");
    private final By expectedTablesHeaderLocator = By.linkText("iPad 2020 32gb wi-fi");
    final int delay = 10;
    private WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {

        driver.quit();

    }

    @Test
    public void productCatalogPage_SubMenuLinks_AllLinksWork() {

        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        var uncategorizedLink = driver.findElement(uncategorizedLocator).getAttribute("href");
        var uncategorizedTitle = driver.findElement(uncategorizedLocator).getAttribute("text").toLowerCase();
        driver.findElement(uncategorizedLocator).click();
        var uncategorizedPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Без категории`!",
                uncategorizedTitle, uncategorizedPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Без категории`!",
                uncategorizedLink, driver.getCurrentUrl());

        var appliancesLink = driver.findElement(appliancesLocator).getAttribute("href");
        var appliancesTitle = driver.findElement(appliancesLocator).getAttribute("text").toLowerCase();
        driver.findElement(appliancesLocator).click();
        var appliancesPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Бытовая техника`!",
                appliancesTitle, appliancesPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Бытовая техника`!",
                appliancesLink, driver.getCurrentUrl());

        var catalogLink = driver.findElement(catalogLocator).getAttribute("href");
        var catalogTitle = driver.findElement(catalogLocator).getAttribute("text").toLowerCase();
        driver.findElement(catalogLocator).click();
        var catalogPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Каталог`!",
                catalogTitle, catalogPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Каталог`!",
                catalogLink, driver.getCurrentUrl());

        var booksLink = driver.findElement(booksLocator).getAttribute("href");
        var booksTitle = driver.findElement(booksLocator).getAttribute("text").toLowerCase();
        driver.findElement(booksLocator).click();
        var booksPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Книги`!",
                booksTitle, booksPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Книги`!",
                booksLink, driver.getCurrentUrl());

        var clothesLink = driver.findElement(clothesLocator).getAttribute("href");
        var clothesTitle = driver.findElement(clothesLocator).getAttribute("text").toLowerCase();
        driver.findElement(clothesLocator).click();
        var clothesPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Одежда`!",
                clothesTitle, clothesPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Одежда`!",
                clothesLink, driver.getCurrentUrl());

        var padLink = driver.findElement(padLocator).getAttribute("href");
        var padTitle = driver.findElement(padLocator).getAttribute("text").toLowerCase();
        driver.findElement(padLocator).click();
        var padPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Планшеты`!",
                padTitle, padPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Планшеты`!",
                padLink, driver.getCurrentUrl());

        var washLink = driver.findElement(washLocator).getAttribute("href");
        var washTitle = driver.findElement(washLocator).getAttribute("text").toLowerCase();
        driver.findElement(washLocator).click();
        var washPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Стиральные машины`!",
                washTitle, washPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Стиральные машины`!",
                washLink, driver.getCurrentUrl());

        var tvLink = driver.findElement(tvLocator).getAttribute("href");
        var tvTitle = driver.findElement(tvLocator).getAttribute("text").toLowerCase();
        driver.findElement(tvLocator).click();
        var tvPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Телевизоры`!",
                tvTitle, tvPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Телевизоры`!",
                tvLink, driver.getCurrentUrl());

        var phonesLink = driver.findElement(phonesLocator).getAttribute("href");
        var phonesTitle = driver.findElement(phonesLocator).getAttribute("text").toLowerCase();
        driver.findElement(phonesLocator).click();
        var phonesPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Телефоны`!",
                phonesTitle, phonesPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Телефоны`!",
                phonesLink, driver.getCurrentUrl());

        var photoVideoLink = driver.findElement(photoVideoLocator).getAttribute("href");
        var photoTitle = driver.findElement(photoVideoLocator).getAttribute("text").toLowerCase();
        driver.findElement(photoVideoLocator).click();
        var photoPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Фото/видео`!",
                photoTitle, photoPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Фото/видео`!",
                photoVideoLink, driver.getCurrentUrl());

        var refrigeratorsLink = driver.findElement(refrigeratorsLocator).getAttribute("href");
        var refrigeratorsTitle = driver.findElement(refrigeratorsLocator).getAttribute("text").toLowerCase();
        driver.findElement(refrigeratorsLocator).click();
        var refrigeratorsPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Холодильники`!",
                refrigeratorsTitle, refrigeratorsPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Холодильники`!",
                refrigeratorsLink, driver.getCurrentUrl());

        var watchLink = driver.findElement(watchLocator).getAttribute("href");
        var watchTitle = driver.findElement(watchLocator).getAttribute("text").toLowerCase();
        driver.findElement(watchLocator).click();
        var watchPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Часы`!",
                watchTitle, watchPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Часы`!",
                watchLink, driver.getCurrentUrl());

        var electronicsLink = driver.findElement(electronicsLocator).getAttribute("href");
        var electronicsTitle = driver.findElement(electronicsLocator).getAttribute("text").toLowerCase();
        driver.findElement(electronicsLocator).click();
        var electronicsPageTitle = driver.findElement(pageTitle).getText().toLowerCase();
        Assert.assertEquals("Не совпадают заголовки открывшейся страницы по ссылке `Электроника`!",
                electronicsTitle, electronicsPageTitle);
        Assert.assertEquals("Не совпадают URL открывшейся страницы и ссылки `Электроника`!",
                electronicsLink, driver.getCurrentUrl());

    }

    @Test
    public void productCatalogPage_CatalogSection_ItemsAddedToCart() {

        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        var itemOne = "Стиральная машина BEKO WRE64P1BWW, фронтальная, 6кг, 800об/мин";
        var itemTwo = "Холодильник БИРЮСА Б-M10, однокамерный, серебристый";
        var itemThree = "iPad 2020 32gb wi-fi";

        driver.findElement(linkHouseholdEquipmentCatalogListLocator).click();
        driver.findElement(addBasketButton).click();
        driver.findElement(moreInformationButtonLocator).click();
        driver.findElement(catalogButton).click();
        driver.findElement(linkHouseholdEquipmentCatalogListLocator).click();
        driver.findElement(addRefrigeratorBasketButton).click();
        driver.findElement(moreInformationButtonLocator).click();
        driver.findElement(catalogButton).click();
        driver.findElement(tablesButtonLocator).click();
        driver.findElement(tablesCardAddButtonLocator).click();
        driver.findElement(moreInformationButtonLocator).click();

        Assert.assertEquals("Данные в корзине не соответствуют выбранной в каталоге модели BEKO WRE64P1BWW",
                itemOne, driver.findElement(expectedHeaderResultLocator).getText());
        Assert.assertEquals("Данные в корзине не соответствуют выбранной в каталоге модели БИРЮСА Б-M10",
                itemTwo, driver.findElement(refrigeratorExpectedHeaderResultLocator).getText());
        Assert.assertEquals("Данные в корзине не соответствуют выбранной в каталоге модели iPad 2020 32gb wi-fi",
                itemThree, driver.findElement(expectedTablesHeaderLocator).getText());

    }

}

