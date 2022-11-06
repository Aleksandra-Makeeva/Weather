import org.openqa.selenium.By;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AleksandraMakeevaTest {

    /*
     *  TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
     * и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test

    public void testLinkNameAndTitle_WhenClickingOnTheButtonGuide() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/home/aleksandra/project/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        String url = "https://openweathermap.org/";
        String expectedResultLink = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement menuGuide = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[text()='Guide']")
        );
        menuGuide.click();
        Thread.sleep(1000);

        String actualResultLink = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();
    }

    /*
     * TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test

    public void testFahrenheitTemperatureWhenClickingOnTheImperialLink() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/aleksandra/project/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        String url = "https://openweathermap.org/";

        String expectedResult = "°F";

        driver.get(url);
        Thread.sleep(5000);

        WebElement imperial = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[text()='Imperial: °F, mph']")
        );
        imperial.click();
        Thread.sleep(1000);

        WebElement temperature = driver.findElement(
          By.xpath("//div[@class='current-container mobile-padding']//span[@class='heading']")
        );
        Thread.sleep(1000);

        String actualResult = temperature.getText().replaceAll("\\d","");

        Thread.sleep(5000);
        Assert.assertEquals(actualResult, expectedResult);
    }


    /*
     * TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом
     * “We use cookies which are essential for the site to work.
     * We also use non-essential cookies to help us improve our services.
     * Any data collected is anonymised. You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void testFooterPresenceOfAPanelWithText() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/aleksandra/project/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        String url = "https://openweathermap.org/";
        String expectedResultText = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResultButtonAllow = "Allow all";
        String expectedResultButtonManageCookies = "Manage cookies";

        driver.get(url);
        Thread.sleep(5000);

        WebElement panelText = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//p[@class='stick-footer-panel__description']")
        );

        String actualResultText = panelText.getText();

        WebElement buttonButtonAllow = driver.findElement(
          By.xpath("//div[@id='stick-footer-panel']//button[@type='button']")
        );

        String actualResultButtonAllow = buttonButtonAllow.getText();

        WebElement buttonManageCookies = driver.findElement(
          By.xpath("//div[@id='stick-footer-panel']//a")
        );

        String actualResultButtonManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultText,expectedResultText);
        Assert.assertEquals(actualResultButtonAllow, expectedResultButtonAllow);
        Assert.assertEquals(actualResultButtonManageCookies, expectedResultButtonManageCookies);

        driver.quit();
    }

    /*
     * TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями
     * “FAQ”, “How to start” и “Ask a question”
     */

    @Test

    public void testMenuSupport() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/home/aleksandra/project/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        String url = "https://openweathermap.org/";

        String expectedResultLinkFAQ = "FAQ";
        String expectedResultLinkHowToStart = "How to start";
        String expectedResultLinkAskAQuestion = "Ask a question";

        driver.get(url);

        Thread.sleep(5000);

        WebElement support = driver.findElement(
          By.xpath("//div[@id='support-dropdown']")
        );

        support.click();

        Thread.sleep(1000);

        WebElement linkFaq = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[1]")
        );

        String actualResultLinkFAQ = linkFaq.getText();

        WebElement linkHowToStart = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[2]")
        );

        String actualResultLinkHowToStart = linkHowToStart.getText();

        WebElement linkAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]")
        );

        String actualResultLinkAskAQuestion = linkAskAQuestion.getText();

        Assert.assertEquals(actualResultLinkFAQ, expectedResultLinkFAQ);
        Assert.assertEquals(actualResultLinkHowToStart, expectedResultLinkHowToStart);
        Assert.assertEquals(actualResultLinkAskAQuestion, expectedResultLinkAskAQuestion);

        driver.quit();


    }

    /*
     * TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка
     * “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void name() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/home/aleksandra/project/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        String url = "https://openweathermap.org/";

        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);

        Thread.sleep(5000);

        WebElement supportButton = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        supportButton.click();

        Thread.sleep(1000);

        WebElement linkAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]")
        );

        linkAskAQuestion.click();
        Thread.sleep(5000);

        

        driver.quit();

    }

    /*
     * TC_11_06
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */

    /*
     * TC_11_07
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий,
     * единицы измерения температуры изменились с F на С
     */

    /*
     * TC_11_08
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    /*
     * TC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */


    /*
     * TC_11_10
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */


}
