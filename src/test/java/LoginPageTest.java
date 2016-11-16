import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class LoginPageTest {
    public static final String URL = "https://secure.filelocker.com";
    public static final String CSS_LOGIN_FIELD = "#txtLogin";
    public static final String ERROR_MESSAGE_TEXT = "Unable to verify credentials";
    public static final String LOGIN = "minchekov160@hotmail.com";
    public static final String PASSWORD = "Qw1111";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.get(URL);
    }
    @Test
    public void testSuccessfullLogin() {
        WebElement loginFiled = driver.findElement(By.cssSelector(CSS_LOGIN_FIELD));
        loginFiled.sendKeys(LOGIN);
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));
        passwordField.sendKeys(PASSWORD);
        // Elements are overlapped, that is why we select element with text
        driver.findElement(By.cssSelector("#loginBtnSecText p")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("#menuBox"));
        Assert.assertTrue(elements.size() == 1);
    }
    @Test(dataProvider = "incorrectCredentials")
    public void testLoginWithInvalidCredentials(String login, String password) {
        WebElement loginFiled = driver.findElement(By.cssSelector(CSS_LOGIN_FIELD));
        loginFiled.sendKeys(login);
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));
        passwordField.sendKeys(password);
        // Elements are overlapped, that is why we select element with text
        driver.findElement(By.cssSelector("#loginBtnSecText p")).click();
        String errorMessage = driver.findElement(By.cssSelector("#alertBox center")).getText();
        Assert.assertEquals(errorMessage, ERROR_MESSAGE_TEXT);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @DataProvider
    public Object[][] incorrectCredentials() {
        return new Object[][]{
                {"incorrectLogin", PASSWORD},
                {LOGIN, "incorrectPassword"}
        };
    }

}
